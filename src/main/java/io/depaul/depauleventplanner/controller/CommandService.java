package io.depaul.depauleventplanner.controller;

import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.dao.RegisterEventForm;
import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import io.depaul.depauleventplanner.model.location.LocationStatus;
import io.depaul.depauleventplanner.model.user.User;
import io.depaul.depauleventplanner.repo.event.EventRepository;
import io.depaul.depauleventplanner.repo.location.LocationRepo;
import io.depaul.depauleventplanner.repo.location.LocationRepository;
import io.depaul.depauleventplanner.repo.user.UserRepo;
import io.depaul.depauleventplanner.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {

    final UserRepository userRepository;
    final LocationRepository locationRepository;
    final EventRepository eventRepository;


    public void registerEvent(final RegisterEventForm eventForm, final Organizer organizer) {
        final Location selectedLocation = locationRepository.getLocationByName(eventForm.getLocationName());
        final RegisteredEvent newRegisteredEvent = new RegisteredEvent(buildEventDetails(eventForm, selectedLocation), organizer);
        System.out.println("Persisting new registered event : " + newRegisteredEvent);
        eventRepository.persistNewEvent(newRegisteredEvent);
    }

    public void reserveSpot(final String eventId, final Participant participant) {
        eventRepository.addReservation(eventId, participant);
    }

    public void cancelEvent(final User currentUser, final String eventId) {
        final RegisteredEvent event = eventRepository.getEvent(eventId);
        if (currentUser.equals(event.getEventOrganizer())) {
            eventRepository.removeEvent(event);
        }

    }

    public void cancelReservation() {}

    public List<RegisteredEvent> getEventsLinkedToUser(final String username) {
        return eventRepository.all().stream()
                .filter(
                        regEvent -> !regEvent.userNotReservedForEvent(username) || regEvent.getEventOrganizer().getUsername().equals(username)
                )
                .collect(Collectors.toList());
    }

    public List<RegisteredEvent> getUpcomingEvents() {
        return eventRepository.all();
    }

    public RegisteredEvent getEventWithId(final String id) {
        return eventRepository.getEvent(id);
    }

    public void cancelReservation(final String eventId, final String username) {
        eventRepository.removeReservation(eventId, username);
    }


    public List<Location> getAvailableLocations() {
        return locationRepository.all().stream().filter(
                location -> location.getStatus() == LocationStatus.AVAILABLE
        ).collect(Collectors.toList());
    }



    private EventDetails buildEventDetails(final RegisterEventForm eventForm, final Location location) {
        return EventDetails.builder()
                .name(eventForm.getEventTitle())
                .startDate(LocalDateTime.parse(eventForm.getStartDate()))
                .endDate(LocalDateTime.parse(eventForm.getEndDate()))
                .imageLocation(eventForm.getImgLocation())
                .description(eventForm.getDescription())
                .location(location)
                .build();
    }
}

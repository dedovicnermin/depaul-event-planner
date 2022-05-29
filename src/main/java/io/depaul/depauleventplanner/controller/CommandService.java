package io.depaul.depauleventplanner.controller;

import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.user.User;
import io.depaul.depauleventplanner.repo.event.EventRepository;
import io.depaul.depauleventplanner.repo.location.LocationRepo;
import io.depaul.depauleventplanner.repo.location.LocationRepository;
import io.depaul.depauleventplanner.repo.user.UserRepo;
import io.depaul.depauleventplanner.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {

    final UserRepository userRepository;
    final LocationRepository locationRepository;
    final EventRepository eventRepository;


    public boolean registerEvent() {
        return false;
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




}

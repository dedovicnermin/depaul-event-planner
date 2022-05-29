package io.depaul.depauleventplanner.controller;

import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.repo.event.EventRepository;
import io.depaul.depauleventplanner.repo.location.LocationRepo;
import io.depaul.depauleventplanner.repo.location.LocationRepository;
import io.depaul.depauleventplanner.repo.user.UserRepo;
import io.depaul.depauleventplanner.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    final UserRepository userRepository;
    final LocationRepository locationRepository;
    final EventRepository eventRepository;


    public boolean registerEvent() {
        return false;
    }

    public boolean reserveSpot() {
        return false;
    }

    public void cancelEvent() {}

    public void cancelReservation() {}

    public List<RegisteredEvent> getEventsLinkedToUser() {
        return null;
    }

    public List<RegisteredEvent> getUpcomingEvents() {
        return eventRepository.all();
    }

    public RegisteredEvent getEventWithId(final String id) {
        return eventRepository.getEvent(id);
    }




}

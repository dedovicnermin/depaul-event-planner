package io.depaul.depauleventplanner.repo.event;

import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.model.RegisteredEvent;

import java.util.List;

public interface EventRepository {
    List<RegisteredEvent> all();
    void save(RegisteredEvent registeredEvent);
    RegisteredEvent removeEvent(RegisteredEvent event);
    RegisteredEvent getEvent(String identifier);
    void removeReservation(String eventId, String username);
    void addReservation(String eventId, Participant username);
}

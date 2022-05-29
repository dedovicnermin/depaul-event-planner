package io.depaul.depauleventplanner.repo.event;

import io.depaul.depauleventplanner.model.RegisteredEvent;

import java.util.List;

public interface EventRepository {
    List<RegisteredEvent> all();
    void save(RegisteredEvent registeredEvent);
    RegisteredEvent removeEvent(RegisteredEvent event);
    RegisteredEvent getEvent(String identifier);
}

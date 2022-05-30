package io.depaul.depauleventplanner.repo.event;

import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RegisteredEventRepo implements EventRepository {
    private final Map<String, RegisteredEvent> eventMap;

    public RegisteredEventRepo(Map<String, RegisteredEvent> eventMap) {
        this.eventMap = eventMap;
    }

    @Override
    public List<RegisteredEvent> all() {
        return new ArrayList<>(eventMap.values());
    }

    @Override
    public void save(RegisteredEvent registeredEvent) {
        eventMap.put(registeredEvent.getId(), registeredEvent);
    }


    @Override
    public RegisteredEvent removeEvent(RegisteredEvent event) {
        return eventMap.remove(event.getId());
    }

    @Override
    public RegisteredEvent getEvent(String id) {
        return eventMap.get(id);
    }

    @Override
    public void removeReservation(String eventId, String username) {
        final RegisteredEvent registeredEvent = eventMap.get(eventId);
        registeredEvent.removeParticipant(username);
        save(registeredEvent);
    }

    @Override
    public void addReservation(String eventId, Participant participant) {
        final RegisteredEvent registeredEvent = eventMap.get(eventId);
        registeredEvent.addParticipant(participant);
        save(registeredEvent);
    }

    @Override
    public void persistNewEvent(RegisteredEvent event) {
        eventMap.put(event.getId(), event);
    }
}

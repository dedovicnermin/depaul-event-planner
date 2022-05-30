package io.depaul.depauleventplanner.model;

import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.behavior.Participant;
import lombok.ToString;

import java.util.*;

@ToString
public class RegisteredEvent {
    private final String id;
    private final EventDetails details;
    private final Organizer eventOrganizer;
    Map<String, Participant> participantMap = new HashMap<>();

    public RegisteredEvent(EventDetails details, Organizer eventOrganizer) {
        id = UUID.randomUUID().toString();
        this.details = details;

        this.eventOrganizer = eventOrganizer;
    }

    public String getId() {
        return id;
    }

    public EventDetails getDetails() {
        return details;
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participantMap.values());
    }

    public String getParticipantCount() {
        return ""+participantMap.values().size();
    }

    public Organizer getEventOrganizer() {
        return eventOrganizer;
    }

    public void addParticipant(final Participant participant) {
        if (Integer.parseInt(getParticipantCount()) == details.getLocation().getMaxCapacity()) {
            throw new RuntimeException("Reservations already at maximum capacity!! Could not successfully reserve.");
        }
        participantMap.put(participant.getUsername(), participant);
    }

    public void removeParticipant(final String username) {
        participantMap.remove(username);
    }

    public boolean userNotReservedForEvent(final String username) {
        return !Objects.nonNull(participantMap.get(username));
    }
}

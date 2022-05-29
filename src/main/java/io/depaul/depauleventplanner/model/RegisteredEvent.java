package io.depaul.depauleventplanner.model;

import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.behavior.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegisteredEvent {
    private final String id;
    private final EventDetails details;
    private final List<Participant> participants;
    private final Organizer eventOrganizer;

    public RegisteredEvent(EventDetails details, Organizer eventOrganizer) {
        id = UUID.randomUUID().toString();
        this.details = details;
        this.participants = new ArrayList<>();
        this.eventOrganizer = eventOrganizer;
    }

    public String getId() {
        return id;
    }

    public EventDetails getDetails() {
        return details;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public int getParticipantCount() {
        return participants.size();
    }

    public Organizer getEventOrganizer() {
        return eventOrganizer;
    }

    public void addParticipant(final Participant participant) {
        this.participants.add(participant);
    }
}

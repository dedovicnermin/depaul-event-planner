package io.depaul.depauleventplanner.behavior;

public interface Attendee {
    String displayName();
    boolean canParticipate();
    boolean canOrganize();
}

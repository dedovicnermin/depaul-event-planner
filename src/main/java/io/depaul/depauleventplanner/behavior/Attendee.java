package io.depaul.depauleventplanner.behavior;

public interface Attendee {
    String displayName();
    String getUsername();
    boolean canParticipate();
    boolean canOrganize();
}

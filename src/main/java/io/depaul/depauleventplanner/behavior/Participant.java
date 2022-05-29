package io.depaul.depauleventplanner.behavior;

public interface Participant extends Attendee {

    @Override
    default boolean canParticipate() {
        return true;
    }

    @Override
    default boolean canOrganize() {
        return false;
    }
}

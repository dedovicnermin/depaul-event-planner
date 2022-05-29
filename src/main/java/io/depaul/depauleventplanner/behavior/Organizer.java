package io.depaul.depauleventplanner.behavior;

public interface Organizer extends Attendee {

    @Override
    default boolean canParticipate() {
        return true;
    }

    @Override
    default boolean canOrganize() {
        return true;
    }
}

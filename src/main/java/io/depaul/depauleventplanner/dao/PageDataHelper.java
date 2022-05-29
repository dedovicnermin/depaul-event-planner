package io.depaul.depauleventplanner.dao;

import io.depaul.depauleventplanner.behavior.Attendee;
import io.depaul.depauleventplanner.model.RegisteredEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class PageDataHelper {

    public String formatDate(LocalDateTime dateTime) {
        String template = "%d/%d/%d %d:%d";
        return String.format(template, dateTime.getMonthValue(), dateTime.getDayOfMonth(), dateTime.getYear(), dateTime.getHour(), dateTime.getMinute());
    }

    public String getEventName(final RegisteredEvent event) {
        return event.getDetails().getName() +"\t" + getOrganizerName(event);
    }

    public String getOrganizerName(final RegisteredEvent event) {
        return ofNullable(event.getEventOrganizer()).map(Attendee::displayName).orElse("Unkown");
    }


    public String getLocationName(final RegisteredEvent event) {
        return event.getDetails().getLocation().getName();
    }

    public int getCapacity(final RegisteredEvent event) {
        return event.getDetails().getLocation().getMaxCapacity();
    }

    public String getLocationAddress(final RegisteredEvent event) {
        return event.getDetails().getLocation().getAddress();
    }

    public List<String> getParticipantDisplayNames(final RegisteredEvent registeredEvent) {
        return registeredEvent.getParticipants().stream()
                .map(Attendee::displayName)
                .collect(Collectors.toList());
    }



    public String getStartDate(final RegisteredEvent event) {
        return formatDate(event.getDetails().getStartDate());
    }

    public String getEndDate(final RegisteredEvent event) {
        return formatDate(event.getDetails().getEndDate());
    }

    public String getImagePath(final RegisteredEvent event) {
        return event.getDetails().getImageLocation();
    }

    public String getDescription(final RegisteredEvent event) {
        return event.getDetails().getName();
    }







}

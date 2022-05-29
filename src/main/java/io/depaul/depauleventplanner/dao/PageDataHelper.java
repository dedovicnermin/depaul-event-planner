package io.depaul.depauleventplanner.dao;

import io.depaul.depauleventplanner.behavior.Attendee;
import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.config.auth.AppUserDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.user.UserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class PageDataHelper {

    // === EVENT DETAILS ===

    public static String getStartDate(final RegisteredEvent event) {
        return formatDate(event.getDetails().getStartDate());
    }

    public static String getEventName(final RegisteredEvent event) {
        return event.getDetails().getName() +"\t" + getOrganizerName(event);
    }

    public static String getOrganizerName(final RegisteredEvent event) {
        return ofNullable(event.getEventOrganizer()).map(Attendee::displayName).map(s -> "(" + s + ")").orElse("(Unkown)");
    }

    public static List<String> getParticipantDisplayNames(final RegisteredEvent registeredEvent) {
        return registeredEvent.getParticipants().stream()
                .map(Attendee::displayName)
                .collect(Collectors.toList());
    }


    public static String getEndDate(final RegisteredEvent event) {
        return formatDate(event.getDetails().getEndDate());
    }

    public static String getImagePath(final RegisteredEvent event) {
        return event.getDetails().getImageLocation();
    }

    public static String getDescription(final RegisteredEvent event) {
        return event.getDetails().getName();
    }

    public static int getAttendingCount(final RegisteredEvent event) {
        return event.getParticipants().size();
    }


    // === LOCATION ===

    public static String getLocationName(final RegisteredEvent event) {
        return event.getDetails().getLocation().getName();
    }

    public static int getCapacity(final RegisteredEvent event) {
        return event.getDetails().getLocation().getMaxCapacity();
    }

    public static String getLocationAddress(final RegisteredEvent event) {
        return event.getDetails().getLocation().getAddress();
    }

    public static String formatDate(LocalDateTime dateTime) {
        String template = "%d/%d/%d %d:%d";
        return String.format(template, dateTime.getMonthValue(), dateTime.getDayOfMonth(), dateTime.getYear(), dateTime.getHour(), dateTime.getMinute());
    }



    // === conditional logic ===

    public boolean showCancelOption(AppUserDetails user, RegisteredEvent event) {
        return Optional.of(user)
                .map(AppUserDetails::getUserType)
                .map(type -> type == UserType.FACULTY && event.getEventOrganizer().equals(user.getUser()))
                .orElseThrow();
    }


    public static PopupData determineButtonOption(final RegisteredEvent event, AppUserDetails userDetails) {
        if (userDetails.getUser().getUserType() == UserType.FACULTY) {
            if (event.getEventOrganizer().getUsername().equals(userDetails.getUsername())) {
                return cancelEventPopup(event.getId());
            } else {
                final long match = event.getParticipants().stream()
                        .filter(participant -> !participant.getUsername().equals(userDetails.getUsername()))
                        .count();
                // faculty can reserve a spot
                if (match == 0) {
                    return reserveSpotPopup(event.getId());
                } else {
                    return cancelReservationPopup(event.getId());
                }

            }
        } else {
            final long alreadyReserved = event.getParticipants().stream()
                    .filter(participant -> participant.getUsername().equals(userDetails.getUsername()))
                    .count();
            if (alreadyReserved == 0) {
                return reserveSpotPopup(event.getId());
            } else {
                return cancelReservationPopup(event.getId());
            }
        }
    }

    public static PopupData cancelEventPopup(final String eventId) {
        return new PopupData(
                "CANCEL EVENT",
                "Please confirm you'd like to cancel this event",
                "/events/" + eventId + "/cancel/event"
        );
    }

    public static PopupData cancelReservationPopup(final String eventId) {
        return new PopupData(
                "REMOVE RESERVATION",
                "Please confirm you'd like to cancel your reservation",
                "/events/" + eventId + "/cancel/reservation"
        );
    }

    public static PopupData reserveSpotPopup(final String eventId) {
        return new PopupData(
                "RESERVE SPOT",
                "Please confirm you'd like to reserve a spot at this event",
                "/events/" + eventId + "/reserve"
        );
    }

















}

package io.depaul.depauleventplanner.controller;


import io.depaul.depauleventplanner.behavior.Attendee;
import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.config.auth.AppUserDetails;
import io.depaul.depauleventplanner.dao.PageDataHelper;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventPlannerController {

    final CommandService commandService;


    @GetMapping(value = "/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

    @GetMapping(value = "home")
    public String homePage(final Model model) {
        final AppUserDetails currentUser = getCurrentUser();
        model.addAttribute("user", currentUser.getUser());
        return "homepage";

    }

    @GetMapping(value = "events")
    public String eventsPage(final Model model) {
        model.addAttribute("events", commandService.getUpcomingEvents());
        model.addAttribute("helper", new PageDataHelper());
        return "events";
    }

    @GetMapping(value = "events/{eventId}")
    public String eventPage(@PathVariable String eventId, final Model model) {
        final AppUserDetails currentUser = getCurrentUser();
        final RegisteredEvent registeredEvent = commandService.getEventWithId(eventId);
        model.addAttribute("user", currentUser);
        model.addAttribute("event", registeredEvent);
        model.addAttribute("helper", new PageDataHelper());
        model.addAttribute("popupData", PageDataHelper.determineButtonOption(registeredEvent, currentUser));
        return "specificEvent";
    }

    @GetMapping(value = "events/user")
    public String viewEventsLinkedToUser(final Model model) {
        final List<RegisteredEvent> linkedToUser = commandService.getEventsLinkedToUser(getCurrentUser().getUsername());
        model.addAttribute("events", linkedToUser);
        model.addAttribute("helper", new PageDataHelper());
        return "events";
    }


    @GetMapping(value = "events/{eventId}/cancel/reservation")
    public RedirectView handleCancelReservation(@PathVariable String eventId) {
        final AppUserDetails currentUser = getCurrentUser();
        commandService.cancelReservation(eventId, currentUser.getUsername());
        return new RedirectView("/events");
    }

    @GetMapping(value = "events/{eventId}/cancel/event")
    public RedirectView handleCancelEventRequest(@PathVariable String eventId) {
        final AppUserDetails currentUser = getCurrentUser();
        commandService.cancelEvent(currentUser.getUser(), eventId);
        return new RedirectView("/events");
    }


    @GetMapping(value = "events/{eventId}/reserve")
    public RedirectView handleReserveRequest(@PathVariable String eventId) {
        final AppUserDetails currentUser = getCurrentUser();
        commandService.reserveSpot(eventId, (Participant) currentUser.getUser());
        return new RedirectView("/events");
    }

    @GetMapping(value = "events/register")
    public String handleRegisterEventRequest(final Model model) {
        return "blank";
    }







    public AppUserDetails getCurrentUser() {
        return (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}

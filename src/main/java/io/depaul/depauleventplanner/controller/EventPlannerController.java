package io.depaul.depauleventplanner.controller;


import io.depaul.depauleventplanner.behavior.Attendee;
import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.config.auth.AppUserDetails;
import io.depaul.depauleventplanner.dao.PageDataHelper;
import io.depaul.depauleventplanner.dao.RegisterEventForm;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String handleGetRegisterEventRequest(final Model model) {
        List<Location> locations = commandService.getAvailableLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("images", List.of("/images/event1.jpeg", "/images/event2.jpeg", "/images/event3.jpeg"));
        model.addAttribute("dao", new RegisterEventForm());
        return "registerEvent";
    }

    @PostMapping(value = "events/register")
    public RedirectView handlePostRegisterEventRequest(@ModelAttribute RegisterEventForm eventForm, final Model model) {
        System.out.println(eventForm);
        commandService.registerEvent(eventForm, (Organizer) getCurrentUser().getUser());
        return new RedirectView("/home");
    }







    public AppUserDetails getCurrentUser() {
        return (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}

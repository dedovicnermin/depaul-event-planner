package io.depaul.depauleventplanner.controller;


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
        model.addAttribute("currentUser", currentUser);
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

    @GetMapping(value = "events/{eventId}/cancel/reservation")
    public RedirectView handleCancelReservation(@PathVariable String eventId) {
        final AppUserDetails currentUser = getCurrentUser();
        commandService.cancelReservation(eventId, currentUser.getUsername());
        return new RedirectView("/events");
    }

    @GetMapping(value = "events/{eventId}/reserve")
    public RedirectView handleReserveRequest(@PathVariable String eventId) {
        final AppUserDetails currentUser = getCurrentUser();
        commandService.reserveSpot(eventId, (Participant) currentUser.getUser());
        return new RedirectView("/events");
    }







    public AppUserDetails getCurrentUser() {
        return (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}

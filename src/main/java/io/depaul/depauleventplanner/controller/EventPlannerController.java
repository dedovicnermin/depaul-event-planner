package io.depaul.depauleventplanner.controller;


import io.depaul.depauleventplanner.config.auth.AppUserDetails;
import io.depaul.depauleventplanner.dao.PageDataHelper;
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
        return "specificEvent";
    }





    public AppUserDetails getCurrentUser() {
        return (AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}

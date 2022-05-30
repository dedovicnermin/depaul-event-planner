package io.depaul.depauleventplanner.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler
    public String error (final Exception e, final Model model) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("cause", e.getCause().getMessage());
        return "error";
    }

}

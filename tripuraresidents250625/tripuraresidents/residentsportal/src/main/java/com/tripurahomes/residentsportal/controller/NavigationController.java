package com.tripurahomes.residentsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/events")
    public String eventsPage() {
        return "events";
    }

    @GetMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping("/myprofile")
    public String myProfilePage() {
        return "myprofile";
    }

    @GetMapping("/settings")
    public String settingsPage() {
        return "settings";
    }

    // ✅ Optional logout redirect (handled by Spring Security)
    @GetMapping("/logout")
    public String logoutPage() {
        return "redirect:/login?logout";
    }
}

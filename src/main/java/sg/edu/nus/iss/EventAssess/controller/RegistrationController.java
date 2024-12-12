package sg.edu.nus.iss.EventAssess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
public class RegistrationController {
    @GetMapping("/register/{eventId}")
    public String displayRegistrationForm(@PathVariable String eventId, @RequestParam String eventName, @RequestParam String eventDate, Model model) {
        model.addAttribute("eventName", eventName);
        model.addAttribute("eventDate", eventDate);
        return "register";
    }
    
}

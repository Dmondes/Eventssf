package sg.edu.nus.iss.EventAssess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.EventAssess.service.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.EventAssess.model.Event;





@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private DatabaseService dbService;

    @GetMapping("/listing")
    public String displayEvents(Model model) {
        List<Event> events = dbService.getAllEvents();
        model.addAttribute("events", events);
        return "listing";
    }
    


    
    
}

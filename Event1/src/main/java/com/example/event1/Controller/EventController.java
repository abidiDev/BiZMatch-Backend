package com.example.event1.Controller;

import com.example.event1.Entity.Event;
import com.example.event1.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/event")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private EventService eventService;



    public EventController(EventService eventService){

        this.eventService= eventService;
    }
    @PostMapping("/add")
    public Event createdEvent (@RequestBody Event event) {
        return eventService.createEvent(event);

    }

    @GetMapping("/get/{eventId}")
    public Event getEventById(@PathVariable Long eventId){
        return eventService.getEventById(eventId);
    }
    @GetMapping("/get/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/update/{eventId}")
    public Event updateEvent(@PathVariable Long eventId ,@RequestBody Event event){
        return eventService.updateEvent(eventId ,event);
    }
    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId)
    {
        eventService.deleteEvent(eventId);
    }


}

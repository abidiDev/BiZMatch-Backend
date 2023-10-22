package com.example.event1.Service;

import com.example.event1.Entity.Event;

import java.util.List;

public interface IEventService {

    Event getEventById (long eventId);

    Event createEvent(Event event);

    List<Event> getAllEvents();

    Event updateEvent(Long eventId, Event event);

    void deleteEvent(Long eventId);

    // Event registerUserForEvent (Long eventId , User user);

    //Event UnregisterUserFromEvent (Long eventId, User user);
}


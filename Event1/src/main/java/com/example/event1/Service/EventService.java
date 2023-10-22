package com.example.event1.Service;

import com.example.event1.Entity.Event;
import com.example.event1.Repository.EventRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;
    @Override


    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }



    @Override
    public List<Event> getAllEvents(){return eventRepository.findAll();}

    @Override

    public Event updateEvent(Long eventId, Event event){
        event.setEventId(eventId);
        return eventRepository.save(event);
    }
    @Override

    public void deleteEvent(Long eventId){
        eventRepository.deleteById(eventId);
    }

}


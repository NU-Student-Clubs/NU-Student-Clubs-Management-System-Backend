package com.nu.clubs.clubs_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Event;
import com.nu.clubs.clubs_backend.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event e) {
        return eventRepository.save(e);
    }

    public Event updateEvent(Long id, Event details) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found: " + id));
        if (details.getTitle() != null)
            e.setTitle(details.getTitle());
        if (details.getDescription() != null)
            e.setDescription(details.getDescription());
        if (details.getEventDate() != null)
            e.setEventDate(details.getEventDate());
        if (details.getLocation() != null)
            e.setLocation(details.getLocation());
        if (details.getClubId() != null)
            e.setClubId(details.getClubId());
        return eventRepository.save(e);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found: " + id));
    }

    public java.util.Optional<Event> getEventByIdOptional(Long id) {
        return eventRepository.findById(id);
    }

    public Event getEventOrThrow(Long id) {
        return getEventById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id))
            throw new NotFoundException("Event not found: " + id);
        eventRepository.deleteById(id);
    }

    // Wrapper methods for controller compatibility
    public List<Event> getEventsByClub(Long clubId) {
        return eventRepository.findByClubId(clubId);
    }

    public Event saveEvent(Event event) {
        return createEvent(event);
    }
}

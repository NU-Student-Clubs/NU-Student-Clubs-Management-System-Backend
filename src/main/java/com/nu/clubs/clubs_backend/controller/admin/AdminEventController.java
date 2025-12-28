package com.nu.clubs.clubs_backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.dto.EventRequest;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Event;
import com.nu.clubs.clubs_backend.repository.ClubRepository;
import com.nu.clubs.clubs_backend.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventRequest request) {
        Event event = new Event();
        applyRequest(event, request, true);
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody EventRequest request) {
        Event existing = eventService.getEventOrThrow(id);
        applyRequest(existing, request, false);
        return ResponseEntity.ok(eventService.updateEvent(id, existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> list() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    private void applyRequest(Event target, EventRequest request, boolean isCreate) {
        if (request.getTitle() != null) {
            target.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            target.setDescription(request.getDescription());
        }
        if (request.getEventDate() != null) {
            target.setEventDate(request.getEventDate());
        }
        if (request.getLocation() != null) {
            target.setLocation(request.getLocation());
        }
        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubId()));
            target.setClubId(club.getId());
        } else if (isCreate) {
            throw new NotFoundException("Club is required for the event");
        }
    }
}

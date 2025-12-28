package com.nu.clubs.clubs_backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.dto.CommitteeRequest;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Committee;
import com.nu.clubs.clubs_backend.repository.ClubRepository;
import com.nu.clubs.clubs_backend.service.CommitteeService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/committees")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminCommitteeController {

    @Autowired
    private CommitteeService service;

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Committee> create(@RequestBody CommitteeRequest request) {
        Committee committee = new Committee();
        applyRequest(committee, request, true);
        return ResponseEntity.ok(service.createCommittee(committee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Committee> update(@PathVariable Long id, @RequestBody CommitteeRequest request) {
        Committee existing = service.getCommitteeById(id);
        applyRequest(existing, request, false);
        return ResponseEntity.ok(service.updateCommittee(id, existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCommittee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Committee>> list() {
        return ResponseEntity.ok(service.getAllCommittees());
    }

    private void applyRequest(Committee target, CommitteeRequest request, boolean isCreate) {
        if (request.getName() != null) {
            target.setName(request.getName());
        }
        if (request.getDescription() != null) {
            target.setDescription(request.getDescription());
        }
        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubId()));
            target.setClub(club);
        } else if (request.getClubName() != null && !request.getClubName().isBlank()) {
            Club club = clubRepository.findByName(request.getClubName())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubName()));
            target.setClub(club);
        } else if (isCreate) {
            throw new NotFoundException("Club is required for the committee");
        }
    }
}

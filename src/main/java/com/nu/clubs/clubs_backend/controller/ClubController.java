package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.repository.ClubRepository;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@CrossOrigin(origins = "http://localhost:4200")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Club not found: " + id));
        return ResponseEntity.ok(club);
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return ResponseEntity.ok(clubRepository.save(club));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club clubDetails) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Club not found: " + id));

        if (clubDetails.getName() != null)
            club.setName(clubDetails.getName());
        if (clubDetails.getDescription() != null)
            club.setDescription(clubDetails.getDescription());
        if (clubDetails.getEmail() != null)
            club.setEmail(clubDetails.getEmail());
        if (clubDetails.getCategory() != null)
            club.setCategory(clubDetails.getCategory());
        if (clubDetails.getPresident() != null)
            club.setPresident(clubDetails.getPresident());

        return ResponseEntity.ok(clubRepository.save(club));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        if (!clubRepository.existsById(id))
            throw new NotFoundException("Club not found: " + id);
        clubRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

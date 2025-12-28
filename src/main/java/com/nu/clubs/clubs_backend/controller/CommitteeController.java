package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.model.Committee;
import com.nu.clubs.clubs_backend.service.CommitteeService;

import java.util.List;

@RestController
@RequestMapping("/api/committees")
@CrossOrigin(origins = "http://localhost:4200")
public class CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    @PostMapping
    public ResponseEntity<Committee> createCommittee(@RequestBody Committee committee) {
        Committee created = committeeService.createCommittee(committee);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Committee> updateCommittee(@PathVariable Long id, @RequestBody Committee committee) {
        Committee updated = committeeService.updateCommittee(id, committee);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Committee> getCommitteeById(@PathVariable Long id) {
        Committee committee = committeeService.getCommitteeById(id);
        return ResponseEntity.ok(committee);
    }

    @GetMapping
    public ResponseEntity<List<Committee>> getAllCommittees() {
        List<Committee> committees = committeeService.getAllCommittees();
        return ResponseEntity.ok(committees);
    }



    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCommittee(@PathVariable Long id) {
        committeeService.deactivateCommittee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{committeeId}/members/{userId}")
    public ResponseEntity<Void> addMemberToCommittee(@PathVariable Long committeeId, @PathVariable Long userId) {
        committeeService.addMemberToCommittee(committeeId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{committeeId}/members/{userId}")
    public ResponseEntity<Void> removeMemberFromCommittee(@PathVariable Long committeeId, @PathVariable Long userId) {
        committeeService.removeMemberFromCommittee(committeeId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommittee(@PathVariable Long id) {
        committeeService.deleteCommittee(id);
        return ResponseEntity.noContent().build();
    }

    // Admin Endpoints
    @PostMapping("/admin")
    public ResponseEntity<Committee> createCommitteeAdmin(@RequestBody Committee committee) {
        Committee created = committeeService.createCommittee(committee);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Committee> updateCommitteeAdmin(@PathVariable Long id, @RequestBody Committee committee) {
        Committee updated = committeeService.updateCommittee(id, committee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteCommitteeAdmin(@PathVariable Long id) {
        committeeService.deleteCommittee(id);
        return ResponseEntity.noContent().build();
    }
}

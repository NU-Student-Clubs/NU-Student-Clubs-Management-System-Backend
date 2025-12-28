package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.model.BoardMember;
import com.nu.clubs.clubs_backend.service.BoardMemberService;

import java.util.List;

@RestController
@RequestMapping("/api/board-members")
@CrossOrigin(origins = "http://localhost:4200")
public class BoardMemberController {

    @Autowired
    private BoardMemberService boardMemberService;

    @PostMapping
    public ResponseEntity<BoardMember> createBoardMember(@RequestBody BoardMember boardMember) {
        BoardMember created = boardMemberService.createBoardMember(boardMember);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardMember> updateBoardMember(@PathVariable Long id, @RequestBody BoardMember boardMember) {
        BoardMember updated = boardMemberService.updateBoardMember(id, boardMember);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardMember> getBoardMemberById(@PathVariable Long id) {
        BoardMember boardMember = boardMemberService.getBoardMemberById(id);
        return ResponseEntity.ok(boardMember);
    }

    @GetMapping
    public ResponseEntity<List<BoardMember>> getAllBoardMembers() {
        List<BoardMember> boardMembers = boardMemberService.getAllBoardMembers();
        return ResponseEntity.ok(boardMembers);
    }



    @GetMapping("/position/{position}")
    public ResponseEntity<List<BoardMember>> getBoardMembersByPosition(@PathVariable String position) {
        List<BoardMember> boardMembers = boardMemberService.getBoardMembersByPosition(position);
        return ResponseEntity.ok(boardMembers);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBoardMember(@PathVariable Long id) {
        boardMemberService.deactivateBoardMember(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardMember(@PathVariable Long id) {
        boardMemberService.deleteBoardMember(id);
        return ResponseEntity.noContent().build();
    }

    // Admin Endpoints
    @PostMapping("/admin")
    public ResponseEntity<BoardMember> createBoardMemberAdmin(@RequestBody BoardMember boardMember) {
        BoardMember created = boardMemberService.createBoardMember(boardMember);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<BoardMember> updateBoardMemberAdmin(@PathVariable Long id, @RequestBody BoardMember boardMember) {
        BoardMember updated = boardMemberService.updateBoardMember(id, boardMember);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteBoardMemberAdmin(@PathVariable Long id) {
        boardMemberService.deleteBoardMember(id);
        return ResponseEntity.noContent().build();
    }
}

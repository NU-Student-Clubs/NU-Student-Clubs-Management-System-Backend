package com.nu.clubs.clubs_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nu.clubs.clubs_backend.dto.MembershipRequest;
import com.nu.clubs.clubs_backend.dto.MembershipResponse;
import com.nu.clubs.clubs_backend.dto.mapper.MembershipMapper;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.service.ClubService;
import com.nu.clubs.clubs_backend.service.MembershipService;
import com.nu.clubs.clubs_backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
@Validated
public class MembershipController {
    private final MembershipService membershipService;
    private final UserService userService;
    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<MembershipResponse>> getAllMemberships() {
        return ResponseEntity.ok(membershipService.findAll()
                .stream()
                .map(MembershipMapper::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResponse> getMembershipById(@PathVariable Long id) {
        Membership membership = membershipService.findById(id);
        return ResponseEntity.ok(MembershipMapper.toResponse(membership));
    }

    @PostMapping
    public ResponseEntity<MembershipResponse> createMembership(@Valid @RequestBody MembershipRequest membershipRequest) {
        User user = userService.getUserById(membershipRequest.getUserId());
        Club club = clubService.getClubById(membershipRequest.getClubId());
        Membership created = membershipService.create(user, club);
        return ResponseEntity.status(HttpStatus.CREATED).body(MembershipMapper.toResponse(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

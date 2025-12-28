package com.nu.clubs.clubs_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.dto.AdminRequest;
import com.nu.clubs.clubs_backend.dto.AdminResponse;
import com.nu.clubs.clubs_backend.model.Admin;
import com.nu.clubs.clubs_backend.service.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponse> createAdmin(@RequestBody AdminRequest admin) {
        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.ok(toResponse(createdAdmin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable Long id, @RequestBody AdminRequest admin) {
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(toResponse(updatedAdmin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return ResponseEntity.ok(toResponse(admin));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        List<AdminResponse> response = admins.stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> permanentlyDeleteAdmin(@PathVariable Long id) {
        adminService.permanentlyDeleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    private AdminResponse toResponse(Admin admin) {
        return new AdminResponse(
                admin.getId(),
                admin.getName(),
                admin.getPosition(),
                admin.getClub(),
                admin.getCommittee(),
                admin.getSeason());
    }
}

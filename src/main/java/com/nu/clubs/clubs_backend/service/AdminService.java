package com.nu.clubs.clubs_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_backend.dto.AdminRequest;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Admin;
import com.nu.clubs.clubs_backend.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Admin createAdmin(AdminRequest request) {
        Admin admin = new Admin();
        applyRequest(admin, request);

        String safeName = request.getName() != null ? request.getName() : "Admin";
        admin.setFirstName(safeName);
        admin.setLastName("");
        admin.setEmail(generateEmail(safeName));
        admin.setPassword("changeme");
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(Long id, AdminRequest request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found with id: " + id));

        applyRequest(admin, request);
        admin.setFirstName(request.getName() != null ? request.getName() : admin.getFirstName());
        admin.setUpdatedAt(System.currentTimeMillis());
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found with id: " + id));
    }

    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Transactional
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new NotFoundException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    public void permanentlyDeleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new NotFoundException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    private void applyRequest(Admin admin, AdminRequest request) {
        if (request == null) {
            return;
        }
        if (request.getName() != null) admin.setName(request.getName());
        if (request.getPosition() != null) admin.setPosition(request.getPosition());
        if (request.getClub() != null) admin.setClub(request.getClub());
        if (request.getCommittee() != null) admin.setCommittee(request.getCommittee());
        if (request.getSeason() != null) admin.setSeason(request.getSeason());
    }

    private String generateEmail(String base) {
        String slug = base == null ? "admin" : base.trim().toLowerCase().replaceAll("[^a-z0-9]+", "-");
        return slug + "+auto-" + UUID.randomUUID().toString().substring(0, 8) + "@admin.local";
    }
}

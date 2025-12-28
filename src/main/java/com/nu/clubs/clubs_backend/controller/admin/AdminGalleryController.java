package com.nu.clubs.clubs_backend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nu.clubs.clubs_backend.dto.GalleryRequest;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Gallery;
import com.nu.clubs.clubs_backend.repository.ClubRepository;
import com.nu.clubs.clubs_backend.service.GalleryService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/gallery")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminGalleryController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Gallery> create(@RequestBody GalleryRequest request) {
        Gallery gallery = new Gallery();
        applyRequest(gallery, request, true);
        return ResponseEntity.ok(galleryService.createGallery(gallery));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gallery> update(@PathVariable Long id, @RequestBody GalleryRequest request) {
        Gallery existing = galleryService.getGalleryById(id);
        applyRequest(existing, request, false);
        return ResponseEntity.ok(galleryService.updateGallery(id, existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        galleryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Gallery>> list() {
        return ResponseEntity.ok(galleryService.getAll());
    }

    private void applyRequest(Gallery target, GalleryRequest request, boolean isCreate) {
        if (request.getTitle() != null) {
            target.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            target.setDescription(request.getDescription());
        }
        if (request.getImageUrl() != null) {
            target.setImageUrl(request.getImageUrl());
        }
        if (request.getClubId() != null) {
            Club club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new NotFoundException("Club not found: " + request.getClubId()));
            target.setClubId(club.getId());
        } else if (isCreate) {
            throw new NotFoundException("Club is required for the gallery item");
        }
    }
}

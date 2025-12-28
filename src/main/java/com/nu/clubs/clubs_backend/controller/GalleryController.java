package com.nu.clubs.clubs_backend.controller;

import com.nu.clubs.clubs_backend.model.Gallery;
import com.nu.clubs.clubs_backend.service.GalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGalleries() {
        return ResponseEntity.ok(galleryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable Long id) {
        return galleryService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Gallery>> getGalleriesByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(galleryService.getByClub(clubId));
    }

    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        return ResponseEntity.ok(galleryService.save(gallery));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable Long id, @RequestBody Gallery gallery) {
        return galleryService.getById(id)
                .map(existing -> {
                    gallery.setId(id);
                    return ResponseEntity.ok(galleryService.save(gallery));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        galleryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

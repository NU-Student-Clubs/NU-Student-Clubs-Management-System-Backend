package com.nu.clubs.clubs_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Gallery;
import com.nu.clubs.clubs_backend.repository.GalleryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery createGallery(Gallery g) {
        return galleryRepository.save(g);
    }

    public Gallery updateGallery(Long id, Gallery details) {
        Gallery g = galleryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gallery item not found: " + id));
        if (details.getTitle() != null)
            g.setTitle(details.getTitle());
        if (details.getDescription() != null)
            g.setDescription(details.getDescription());
        if (details.getImageUrl() != null)
            g.setImageUrl(details.getImageUrl());
        if (details.getClubId() != null)
            g.setClubId(details.getClubId());
        return galleryRepository.save(g);
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gallery item not found: " + id));
    }

    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }

    public void deleteGallery(Long id) {
        if (!galleryRepository.existsById(id))
            throw new NotFoundException("Gallery item not found: " + id);
        galleryRepository.deleteById(id);
    }

    // Wrapper methods for controller compatibility
    public List<Gallery> getAll() {
        return getAllGallery();
    }

    public Optional<Gallery> getById(Long id) {
        return galleryRepository.findById(id);
    }

    public Gallery save(Gallery gallery) {
        return createGallery(gallery);
    }

    public void delete(Long id) {
        deleteGallery(id);
    }

    public List<Gallery> getByClub(Long clubId) {
        return galleryRepository.findByClubId(clubId);
    }
}

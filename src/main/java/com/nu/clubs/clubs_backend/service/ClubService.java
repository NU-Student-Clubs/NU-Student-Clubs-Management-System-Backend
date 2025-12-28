package com.nu.clubs.clubs_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nu.clubs.clubs_backend.exception.BadRequestException;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional
    public Club createClub(Club club) {
        if (clubRepository.findByName(club.getName()).isPresent()) {
            throw new BadRequestException("Club with name '" + club.getName() + "' already exists");
        }
        return clubRepository.save(club);
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Club not found with id: " + id));
    }

    public Page<Club> getAllClubs(Pageable pageable) {
        return clubRepository.findAll(pageable);
    }

    public List<Club> searchClubsByName(String name) {
        return clubRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Club> findByCategory(String category) {
        return clubRepository.findByCategory(category);
    }

    @Transactional
    public Club updateClub(Long id, Club updatedClub) {
        Club existingClub = getClubById(id);

        existingClub.setName(updatedClub.getName());
        existingClub.setDescription(updatedClub.getDescription());
        existingClub.setPresident(updatedClub.getPresident());
        existingClub.setEmail(updatedClub.getEmail());
        existingClub.setCategory(updatedClub.getCategory());

        return clubRepository.save(existingClub);
    }

    @Transactional
    public void deleteClub(Long id) {
        Club club = getClubById(id);
        clubRepository.delete(club);
    }
}

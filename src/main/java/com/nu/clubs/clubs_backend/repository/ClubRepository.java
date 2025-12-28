package com.nu.clubs.clubs_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_backend.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByName(String name);

    List<Club> findByNameContainingIgnoreCase(String name);

    List<Club> findByCategory(String category);
}

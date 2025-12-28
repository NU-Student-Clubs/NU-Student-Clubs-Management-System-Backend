package com.nu.clubs.clubs_backend.repository;

import com.nu.clubs.clubs_backend.model.Committee;
import com.nu.clubs.clubs_backend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    Optional<Committee> findByName(String name);
    List<Committee> findByClub(Club club);
    List<Committee> findByClubAndIsActive(Club club, Boolean isActive);
}

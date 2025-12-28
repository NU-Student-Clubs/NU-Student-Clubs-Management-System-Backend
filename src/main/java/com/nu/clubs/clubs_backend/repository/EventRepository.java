package com.nu.clubs.clubs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByClub(Club club);
    
    List<Event> findByClubId(Long clubId);

    List<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end);
}

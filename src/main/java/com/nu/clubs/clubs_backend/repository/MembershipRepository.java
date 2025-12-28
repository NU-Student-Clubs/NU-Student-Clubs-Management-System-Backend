package com.nu.clubs.clubs_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.model.User;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByStatus(String status);

    List<Membership> findByClub(Club club);

    List<Membership> findByUser(User user);

    List<Membership> findActiveMembershipsByUser(User user);
}

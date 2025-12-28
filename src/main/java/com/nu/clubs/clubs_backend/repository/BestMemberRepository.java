package com.nu.clubs.clubs_backend.repository;

import com.nu.clubs.clubs_backend.model.BestMember;
import com.nu.clubs.clubs_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestMemberRepository extends JpaRepository<BestMember, Integer> {

    List<BestMember> findByUser(User user);

    @Query("SELECT bm FROM BestMember bm WHERE bm.user = :user")
    List<BestMember> findBestMembersByUser(@Param("user") User user);
}
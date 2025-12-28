package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    public Membership findById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membership not found with id: " + id));
    }

    public List<Membership> findByStatus(String status) {
        return membershipRepository.findByStatus(status);
    }

    public List<Membership> findByClub(Club club) {
        return membershipRepository.findByClub(club);
    }

    public List<Membership> findByUser(User user) {
        return membershipRepository.findByUser(user);
    }

    @Transactional
    public Membership create(User user, Club club) {
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setClub(club);
        membership.setStatus("ACTIVE");
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership updateStatus(Long id, String status) {
        Membership membership = findById(id);
        membership.setStatus(status);
        return membershipRepository.save(membership);
    }

    @Transactional
    public void delete(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new NotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }
}

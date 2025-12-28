package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.model.Committee;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.repository.CommitteeRepository;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommitteeService {
    
    @Autowired
    private CommitteeRepository committeeRepository;

    public Committee createCommittee(Committee committee) {
        return committeeRepository.save(committee);
    }

    public Committee updateCommittee(Long id, Committee committeeDetails) {
        Committee committee = committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found with id: " + id));
        
        if (committeeDetails.getName() != null) committee.setName(committeeDetails.getName());
        if (committeeDetails.getDescription() != null) committee.setDescription(committeeDetails.getDescription());
        if (committeeDetails.getHead() != null) committee.setHead(committeeDetails.getHead());
        if (committeeDetails.getPurpose() != null) committee.setPurpose(committeeDetails.getPurpose());
        if (committeeDetails.getIsActive() != null) committee.setIsActive(committeeDetails.getIsActive());
        committee.setUpdatedAt(java.time.LocalDateTime.now());
        
        return committeeRepository.save(committee);
    }

    public Committee getCommitteeById(Long id) {
        return committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found with id: " + id));
    }

    public Optional<Committee> getCommitteeByName(String name) {
        return committeeRepository.findByName(name);
    }

    public List<Committee> getCommitteesByClub(Club club) {
        return committeeRepository.findByClub(club);
    }

    public List<Committee> getActiveCommitteesByClub(Club club) {
        return committeeRepository.findByClubAndIsActive(club, true);
    }

    public List<Committee> getAllCommittees() {
        return committeeRepository.findAll();
    }

    public void deactivateCommittee(Long id) {
        Committee committee = committeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Committee not found with id: " + id));
        committee.setIsActive(false);
        committeeRepository.save(committee);
    }

    public void deleteCommittee(Long id) {
        if (!committeeRepository.existsById(id)) {
            throw new NotFoundException("Committee not found with id: " + id);
        }
        committeeRepository.deleteById(id);
    }

    public void addMemberToCommittee(Long committeeId, Long userId) {
        Committee committee = committeeRepository.findById(committeeId)
                .orElseThrow(() -> new NotFoundException("Committee not found with id: " + committeeId));
        // Member will be added through the relationship
        committeeRepository.save(committee);
    }

    public void removeMemberFromCommittee(Long committeeId, Long userId) {
        Committee committee = committeeRepository.findById(committeeId)
                .orElseThrow(() -> new NotFoundException("Committee not found with id: " + committeeId));
        // Member will be removed through the relationship
        committeeRepository.save(committee);
    }
}

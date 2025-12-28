package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.model.BoardMember;
import com.nu.clubs.clubs_backend.model.Club;
import com.nu.clubs.clubs_backend.repository.BoardMemberRepository;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardMemberService {
    
    @Autowired
    private BoardMemberRepository boardMemberRepository;

    public BoardMember createBoardMember(BoardMember boardMember) {
        return boardMemberRepository.save(boardMember);
    }

    public BoardMember updateBoardMember(Long id, BoardMember boardMemberDetails) {
        BoardMember boardMember = boardMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board Member not found with id: " + id));
        
        if (boardMemberDetails.getEmail() != null) boardMember.setEmail(boardMemberDetails.getEmail());
        if (boardMemberDetails.getFirstName() != null) boardMember.setFirstName(boardMemberDetails.getFirstName());
        if (boardMemberDetails.getLastName() != null) boardMember.setLastName(boardMemberDetails.getLastName());
        if (boardMemberDetails.getPosition() != null) boardMember.setPosition(boardMemberDetails.getPosition());
        if (boardMemberDetails.getClub() != null) boardMember.setClub(boardMemberDetails.getClub());
        if (boardMemberDetails.getIsActiveBoardMember() != null) boardMember.setIsActiveBoardMember(boardMemberDetails.getIsActiveBoardMember());
        boardMember.setUpdatedAt(System.currentTimeMillis());
        
        return boardMemberRepository.save(boardMember);
    }

    public BoardMember getBoardMemberById(Long id) {
        return boardMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board Member not found with id: " + id));
    }

    public Optional<BoardMember> getBoardMemberByEmail(String email) {
        return boardMemberRepository.findByEmail(email);
    }

    public List<BoardMember> getBoardMembersByClub(Club club) {
        return boardMemberRepository.findByClub(club);
    }

    public List<BoardMember> getActiveBoardMembersByClub(Club club) {
        return boardMemberRepository.findByClubAndIsActiveBoardMember(club, true);
    }

    public List<BoardMember> getBoardMembersByPosition(String position) {
        return boardMemberRepository.findByPosition(position);
    }

    public List<BoardMember> getAllBoardMembers() {
        return boardMemberRepository.findAll();
    }

    public void deactivateBoardMember(Long id) {
        BoardMember boardMember = boardMemberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board Member not found with id: " + id));
        boardMember.setIsActiveBoardMember(false);
        boardMemberRepository.save(boardMember);
    }

    public void deleteBoardMember(Long id) {
        if (!boardMemberRepository.existsById(id)) {
            throw new NotFoundException("Board Member not found with id: " + id);
        }
        boardMemberRepository.deleteById(id);
    }
}

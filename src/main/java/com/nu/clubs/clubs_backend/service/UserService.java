package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.dto.UserProfileResponse;
import com.nu.clubs.clubs_backend.exception.NotFoundException;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.model.Membership;
import com.nu.clubs.clubs_backend.model.BestMember;
import com.nu.clubs.clubs_backend.repository.UserRepository;
import com.nu.clubs.clubs_backend.repository.MembershipRepository;
import com.nu.clubs.clubs_backend.repository.BestMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private BestMemberRepository bestMemberRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public UserProfileResponse getUserProfile(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !user.getActive()) {
            throw new NotFoundException("User not found or inactive");
        }

        // Get active memberships
        List<Membership> memberships = membershipRepository.findActiveMembershipsByUser(user);
        List<String> clubNames = memberships.stream()
                .map(m -> m.getClub().getName())
                .collect(Collectors.toList());
        Integer clubsCount = clubNames.size();

        // Check if best member
        List<BestMember> bestMembers = bestMemberRepository.findBestMembersByUser(user);
        Boolean isBestMember = !bestMembers.isEmpty();

        String fullName = user.getFirstName() + " " + user.getLastName();
        return new UserProfileResponse(
                fullName,
                user.getEmail(),
                user.getProfilePicture(),
                user.getPhone(),
                "",
                "",
                user.getActive(),
                clubNames,
                clubsCount,
                isBestMember
        );
    }
}

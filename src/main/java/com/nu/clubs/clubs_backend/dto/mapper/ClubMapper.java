package com.nu.clubs.clubs_backend.dto.mapper;

import com.nu.clubs.clubs_backend.dto.ClubRequest;
import com.nu.clubs.clubs_backend.dto.ClubResponse;
import com.nu.clubs.clubs_backend.model.Club;

public final class ClubMapper {

    private ClubMapper() {}

    public static ClubResponse toResponse(Club club) {
        if (club == null) return null;
        return new ClubResponse(
                club.getId(),
                club.getName(),
                club.getDescription(),
                club.getPresident(),
                club.getEmail(),
                club.getCategory(),
                club.getCreatedAt(),
                club.getUpdatedAt()
        );
    }

    public static Club toEntity(ClubRequest request) {
        if (request == null) return null;
        Club club = new Club();
        club.setName(request.getName());
        club.setDescription(request.getDescription());
        club.setPresident(request.getPresident());
        club.setEmail(request.getEmail());
        club.setCategory(request.getCategory());
        return club;
    }

    public static void updateEntity(ClubRequest request, Club club) {
        if (request == null || club == null) return;
        club.setName(request.getName());
        club.setDescription(request.getDescription());
        club.setPresident(request.getPresident());
        club.setEmail(request.getEmail());
        club.setCategory(request.getCategory());
    }
}

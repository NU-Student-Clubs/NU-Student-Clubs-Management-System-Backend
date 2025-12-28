package com.nu.clubs.clubs_backend.dto.mapper;

import com.nu.clubs.clubs_backend.dto.MembershipResponse;
import com.nu.clubs.clubs_backend.model.Membership;

public final class MembershipMapper {

    private MembershipMapper() {}

    public static MembershipResponse toResponse(Membership membership) {
        if (membership == null) return null;

        return new MembershipResponse(
                membership.getId(),
                membership.getUser() != null ? membership.getUser().getId() : null,
                membership.getClub() != null ? membership.getClub().getId() : null,
                membership.getStatus(),
                membership.getJoinDate(),
                membership.getUpdatedAt()
        );
    }
}

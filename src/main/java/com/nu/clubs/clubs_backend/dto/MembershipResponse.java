package com.nu.clubs.clubs_backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {
    private Long id;
    private Long userId;
    private Long clubId;
    private String status;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
}

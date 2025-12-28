package com.nu.clubs.clubs_backend.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BOARD_MEMBER")
public class BoardMember extends User {
    @Column
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "join_date")
    private Long joinDate = System.currentTimeMillis();

    @Column(name = "is_active_board")
    private Boolean isActiveBoardMember = true;

    public BoardMember() {}

    public BoardMember(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }

    public Long getJoinDate() { return joinDate; }
    public void setJoinDate(Long joinDate) { this.joinDate = joinDate; }

    public Boolean getIsActiveBoardMember() { return isActiveBoardMember; }
    public void setIsActiveBoardMember(Boolean isActiveBoardMember) { this.isActiveBoardMember = isActiveBoardMember; }
}

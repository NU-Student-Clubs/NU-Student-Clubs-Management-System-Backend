package com.nu.clubs.clubs_backend.dto;

public class AdminResponse {
    private Long id;
    private String name;
    private String position;
    private String club;
    private String committee;
    private Integer season;

    public AdminResponse() {
    }

    public AdminResponse(Long id, String name, String position, String club, String committee, Integer season) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.club = club;
        this.committee = committee;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }
}
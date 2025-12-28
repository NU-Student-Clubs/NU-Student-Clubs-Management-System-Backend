package com.nu.clubs.clubs_backend.dto;

import java.util.List;

public class UserProfileResponse {

    private String fullName;
    private String email;
    private String profilePicture;
    private String phoneNumber;
    private String department;
    private String userType;
    private Boolean active;
    private List<String> clubs;
    private Integer clubsCount;
    private Boolean isBestMember;

    // Constructors
    public UserProfileResponse() {}

    public UserProfileResponse(String fullName, String email, String profilePicture, String phoneNumber, String department, String userType, Boolean active, List<String> clubs, Integer clubsCount, Boolean isBestMember) {
        this.fullName = fullName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.userType = userType;
        this.active = active;
        this.clubs = clubs;
        this.clubsCount = clubsCount;
        this.isBestMember = isBestMember;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getClubs() {
        return clubs;
    }

    public void setClubs(List<String> clubs) {
        this.clubs = clubs;
    }

    public Integer getClubsCount() {
        return clubsCount;
    }

    public void setClubsCount(Integer clubsCount) {
        this.clubsCount = clubsCount;
    }

    public Boolean getIsBestMember() {
        return isBestMember;
    }

    public void setIsBestMember(Boolean isBestMember) {
        this.isBestMember = isBestMember;
    }
}
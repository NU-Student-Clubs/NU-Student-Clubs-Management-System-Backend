package com.nu.clubs.clubs_backend.dto;

import java.util.List;

public class UserResponse {
    private Long userId;
    private String email;
    private List<String> roles;
    private String token;

    // Constructors
    public UserResponse() {}

    public UserResponse(Long userId, String email, List<String> roles, String token) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

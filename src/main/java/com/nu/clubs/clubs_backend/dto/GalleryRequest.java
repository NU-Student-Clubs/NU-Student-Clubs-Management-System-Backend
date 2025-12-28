package com.nu.clubs.clubs_backend.dto;

public class GalleryRequest {
    private String title;
    private String description;
    private String imageUrl;
    private Long clubId;

    public GalleryRequest() {}

    public GalleryRequest(String title, String description, String imageUrl, Long clubId) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.clubId = clubId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getClubId() {
        return clubId;
    }
    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }
}



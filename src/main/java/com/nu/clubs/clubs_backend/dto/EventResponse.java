package com.nu.clubs.clubs_backend.dto;

import java.time.LocalDateTime;

public class EventResponse {
	private Long id;
	private String title;
	private String description;
	private LocalDateTime eventDate;
	private String location;
	private Long clubId;

	public EventResponse() {
	}

	public EventResponse(Long id, String title, String description, LocalDateTime eventDate, String location,
			Long clubId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.eventDate = eventDate;
		this.location = location;
		this.clubId = clubId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}
}

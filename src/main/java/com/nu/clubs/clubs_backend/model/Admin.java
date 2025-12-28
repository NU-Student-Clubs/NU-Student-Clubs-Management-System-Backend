package com.nu.clubs.clubs_backend.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    @Column
    private String department;

    @Column
    private String adminLevel;

    @Column(name = "can_manage_admins")
    private Boolean canManageAdmins = false;

    @Column(name = "can_manage_clubs")
    private Boolean canManageClubs = false;

    @Column(name = "can_manage_applications")
    private Boolean canManageApplications = false;

    @Column(name = "display_name")
    private String name;

    @Column(name = "position_title")
    private String position;

    @Column(name = "club_name")
    private String club;

    @Column(name = "committee_name")
    private String committee;

    @Column(name = "season_year")
    private Integer season;

    public Admin() {}

    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }

    public Boolean getCanManageAdmins() { return canManageAdmins; }
    public void setCanManageAdmins(Boolean canManageAdmins) { this.canManageAdmins = canManageAdmins; }

    public Boolean getCanManageClubs() { return canManageClubs; }
    public void setCanManageClubs(Boolean canManageClubs) { this.canManageClubs = canManageClubs; }

    public Boolean getCanManageApplications() { return canManageApplications; }
    public void setCanManageApplications(Boolean canManageApplications) { this.canManageApplications = canManageApplications; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getClub() { return club; }
    public void setClub(String club) { this.club = club; }

    public String getCommittee() { return committee; }
    public void setCommittee(String committee) { this.committee = committee; }

    public Integer getSeason() { return season; }
    public void setSeason(Integer season) { this.season = season; }
}

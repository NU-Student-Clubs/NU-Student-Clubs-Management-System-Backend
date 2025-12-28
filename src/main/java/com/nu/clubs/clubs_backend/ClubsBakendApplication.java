package com.nu.clubs.clubs_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.nu.clubs.clubs_backend.model.Admin;
import com.nu.clubs.clubs_backend.repository.AdminRepository;

@SpringBootApplication
public class ClubsBakendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubsBakendApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner seedAdmin(AdminRepository adminRepository) {
		return args -> {
			if (adminRepository.count() == 0) {
				Admin admin = new Admin("admin@example.com", "password", "Default", "Admin");
				admin.setDepartment("IT");
				admin.setAdminLevel("SUPER");
				admin.setCanManageAdmins(true);
				admin.setCanManageClubs(true);
				adminRepository.save(admin);
			}
		};
	}
}

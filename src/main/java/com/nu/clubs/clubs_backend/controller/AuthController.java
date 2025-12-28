package com.nu.clubs.clubs_backend.controller;

import com.nu.clubs.clubs_backend.dto.LoginRequest;
import com.nu.clubs.clubs_backend.dto.SignupRequest;
import com.nu.clubs.clubs_backend.dto.UserResponse;
import com.nu.clubs.clubs_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = authService.login(loginRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        UserResponse userResponse = authService.signup(signupRequest);
        return ResponseEntity.ok(userResponse);
    }
}

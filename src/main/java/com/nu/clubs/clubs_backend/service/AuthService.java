package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.config.JwtUtil;
import com.nu.clubs.clubs_backend.dto.LoginRequest;
import com.nu.clubs.clubs_backend.dto.SignupRequest;
import com.nu.clubs.clubs_backend.dto.UserResponse;
import com.nu.clubs.clubs_backend.exception.BadRequestException;
import com.nu.clubs.clubs_backend.exception.UnauthorizedException;
import com.nu.clubs.clubs_backend.model.Role;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!user.getActive()) {
            throw new UnauthorizedException("Account is inactive");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return mapToUserResponse(user, token);
    }

    public UserResponse signup(SignupRequest signupRequest) {
        if (!signupRequest.getEmail().endsWith("@nu.edu.eg")) {
            throw new BadRequestException("Email must be a university email ending with @nu.edu.eg");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User(signupRequest.getEmail(),
                encodedPassword,
                signupRequest.getFirstName(),
                signupRequest.getLastName());
        user.setPhone(signupRequest.getPhoneNumber());

        user = userRepository.save(user);

        List<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getEmail(), roleNames);
        return mapToUserResponse(user, token);
    }

    private UserResponse mapToUserResponse(User user, String token) {
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        return new UserResponse(user.getId(), user.getEmail(), roles, token);
    }
}

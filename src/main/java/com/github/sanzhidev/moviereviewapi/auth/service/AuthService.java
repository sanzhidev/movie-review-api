package com.github.sanzhidev.moviereviewapi.auth.service;


import com.github.sanzhidev.moviereviewapi.auth.dto.AuthResponse;
import com.github.sanzhidev.moviereviewapi.auth.dto.LoginRequest;
import com.github.sanzhidev.moviereviewapi.auth.dto.RegisterRequest;
import com.github.sanzhidev.moviereviewapi.domain.user.entity.User;
import com.github.sanzhidev.moviereviewapi.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        String accessToken = jwtService.generateAccesToken(user.getEmail());
        String refrshToken = jwtService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refrshToken);

    }

    public AuthResponse login (LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user =  userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateAccesToken(user.getEmail());
        String refrshToken = jwtService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refrshToken);

    }
}

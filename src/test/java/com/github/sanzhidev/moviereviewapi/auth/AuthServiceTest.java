package com.github.sanzhidev.moviereviewapi.auth;

import com.github.sanzhidev.moviereviewapi.auth.dto.RegisterRequest;
import com.github.sanzhidev.moviereviewapi.auth.service.AuthService;
import com.github.sanzhidev.moviereviewapi.auth.service.JwtService;
import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import com.github.sanzhidev.moviereviewapi.domain.movie.entity.repository.MovieRepository;
import com.github.sanzhidev.moviereviewapi.domain.user.entity.User;
import com.github.sanzhidev.moviereviewapi.domain.user.entity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_shouldReturnTokens_whenValidRequest() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("sanzhar");
        request.setEmail("sanzhar@test.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(new User());
        when(jwtService.generateAccesToken(any(String.class))).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any(String.class))).thenReturn("refreshToken");

        var response = authService.register(request);

        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());

    }
    @Test
    void register_shouldThrowException_whenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("existing@test.com");
        request.setUsername("sanzhar");

        when(userRepository.existsByEmail(any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.register(request));


    }

}

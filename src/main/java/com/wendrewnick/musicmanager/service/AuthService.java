package com.wendrewnick.musicmanager.service;

import com.wendrewnick.musicmanager.dto.AuthRequest;
import com.wendrewnick.musicmanager.dto.AuthResponse;
import com.wendrewnick.musicmanager.repository.UserRepository;
import com.wendrewnick.musicmanager.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        
        var jwtToken = jwtUtil.generateAccessToken(user);
        var refreshToken = jwtUtil.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
         // Basic validation logic
        final String username = jwtUtil.extractUsername(refreshToken);
        if (username != null) {
            var user = userRepository.findByUsername(username).orElseThrow();
            if (jwtUtil.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUtil.generateAccessToken(user);
                // Ideally, rotate refresh token too, but simple re-issue of access token is fine for this scope
                return AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken) // Return same refresh token or new one
                        .build();
            }
        }
        throw new RuntimeException("Invalid refresh token");
    }
}

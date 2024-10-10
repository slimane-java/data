package com.example.data.services;

import com.example.data.dao.UserRepsoitory;
import com.example.data.dto.JwtAuthenticationResponse;
import com.example.data.dto.SignInRequest;
import com.example.data.enitys.Users;
import com.example.data.exception.ResourceNotFoundException;
import com.example.data.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UsersServices {

    private final UserRepsoitory authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signIn(SignInRequest request)  {
        // Authenticate using email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        log.info("User authenticated");
        // Find user by email
        Users user = authRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        log.info("User found: {}", user.getEmail());
        // Check if 2FA is enabled

        // If 2FA is not enabled, proceed with generating tokens
        String jwt = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        log.info("Access token generated: {}", jwt);
        log.info("Refresh token generated: {}",refreshToken);

        log.info("User return object: {}",JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .users(user)
                .build());
        return JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .users(user)
                .build();
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }
    }

}

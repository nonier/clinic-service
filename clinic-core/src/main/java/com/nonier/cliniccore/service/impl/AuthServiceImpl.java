package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.RegistrationDto;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.jwt.JwtAuthentication;
import com.nonier.cliniccore.jwt.JwtProvider;
import com.nonier.cliniccore.jwt.JwtRequest;
import com.nonier.cliniccore.jwt.JwtResponse;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest request) {
        final User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(request.getUsername())));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "incorrect password");
        }
    }

    @Override
    public JwtResponse getAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(username)));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid refresh token");
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(username)));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "incorrect password");
    }

    @Override
    public void register(RegistrationDto registrationDto) {
        userRepository.findByUsername(registrationDto.getUsername())
                .ifPresent(
                        (user) -> {
                            throw new ResponseStatusException(HttpStatus.CONFLICT, "username is already exists");
                        }
                );
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        userRepository.save(user);
    }

    @Override
    public User getUser(Principal principal) {
        return userRepository.findByUsername(((JwtAuthentication) principal).getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(principal.getName())));
    }
}
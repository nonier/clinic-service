package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.RegistrationDto;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.jwt.JwtRequest;
import com.nonier.cliniccore.jwt.JwtResponse;

import java.security.Principal;
import java.util.List;

public interface AuthService {

    JwtResponse login(JwtRequest request);

    JwtResponse getAccessToken(String refreshToken);

    JwtResponse refresh(String refreshToken);

    void register(RegistrationDto registrationDto);

    User getUser(Principal principal);

    List<String> getUserRoleNames(User user);
}
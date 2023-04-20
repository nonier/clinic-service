package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.RegistrationDto;
import com.nonier.cliniccore.jwt.JwtRequest;
import com.nonier.cliniccore.jwt.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest request);

    JwtResponse getAccessToken(String refreshToken);

    JwtResponse refresh(String refreshToken);

    void register(RegistrationDto registrationDto);
}
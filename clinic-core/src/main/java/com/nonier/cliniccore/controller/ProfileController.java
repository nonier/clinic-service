package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<UserDto> getUserProfileInfo(Principal principal) {
        return ResponseEntity.ok(profileService.getUserInfo(principal));
    }

    @PutMapping
    public ResponseEntity<Void> updateUserProfileInfo(Principal principal, @RequestBody UserUpdateDto userUpdateDto) {
        profileService.updateUserInfo(principal, userUpdateDto);
        return ResponseEntity.ok().build();
    }
}
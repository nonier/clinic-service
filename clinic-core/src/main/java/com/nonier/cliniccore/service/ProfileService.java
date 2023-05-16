package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.dto.UserUpdateDto;

import java.security.Principal;

public interface ProfileService {

    UserDto getUserInfo(Principal principal);

    void updateUserInfo(Principal principal, UserUpdateDto userUpdateDto);
}

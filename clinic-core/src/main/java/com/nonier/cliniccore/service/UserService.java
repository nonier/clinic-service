package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto create(UserUpdateDto dto);

    UserDto findById(Long id);

    UserDto update(Long id, UserUpdateDto dto);
}

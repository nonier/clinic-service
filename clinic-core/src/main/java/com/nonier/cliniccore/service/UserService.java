package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.UpdateUserDto;
import com.nonier.cliniccore.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto create(UpdateUserDto dto);

    UserDto findById(Long id);

    UserDto update(Long id, UpdateUserDto dto);
}

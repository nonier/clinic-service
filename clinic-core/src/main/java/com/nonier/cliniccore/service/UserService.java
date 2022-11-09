package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();
}

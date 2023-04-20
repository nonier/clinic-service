package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.RoleDto;
import com.nonier.cliniccore.entity.User;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto create(String name);

    RoleDto findById(Long id);

    List<RoleDto> findRolesByUser(Long userId);
}
package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.RoleDto;
import com.nonier.cliniccore.mapper.RoleMapper;
import com.nonier.cliniccore.repository.RoleRepository;
import com.nonier.cliniccore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::role2RoleDto)
                .toList();
    }
}
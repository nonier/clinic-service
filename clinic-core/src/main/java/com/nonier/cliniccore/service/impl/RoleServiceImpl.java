package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.RoleDto;
import com.nonier.cliniccore.entity.Role;
import com.nonier.cliniccore.mapper.RoleMapper;
import com.nonier.cliniccore.repository.RoleRepository;
import com.nonier.cliniccore.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::role2RoleDto)
                .toList();
    }

    @Override
    public RoleDto create(String name) {
        Role role = new Role();
        role.setName(name);
        return roleMapper.role2RoleDto(roleRepository.save(role));
    }

    @Override
    public RoleDto findById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::role2RoleDto)
                .orElseThrow(() -> new EntityNotFoundException("Role with id: %d not found!".formatted(id)));
    }

    @Override
    public List<RoleDto> findRolesByUser(Long userId) {
        return roleRepository.getRolesByUser(userId)
                .stream()
                .map(roleMapper::role2RoleDto)
                .toList();
    }
}
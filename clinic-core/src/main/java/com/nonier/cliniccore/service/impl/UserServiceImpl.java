package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.entity.UserRole;
import com.nonier.cliniccore.mapper.UserMapper;
import com.nonier.cliniccore.repository.RoleRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.repository.UserRoleRepository;
import com.nonier.cliniccore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::user2UserDto).toList();
    }

    @Override
    public UserDto create(UserUpdateDto dto) {
        User user = userMapper.updateUserDto2User(dto);
        userRepository.save(user);
        List<UserRole> userRoles = dto.getRoleIds()
                .stream()
                .map(roleRepository::getReferenceById)
                .map(role -> UserRole.builder()
                        .user(user)
                        .role(role)
                        .build())
                .toList();
        user.setUserRoles(userRoleRepository.saveAll(userRoles));
        return userMapper.user2UserDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::user2UserDto)
                .orElseThrow(() -> new EntityNotFoundException("User with id: %d not found!".formatted(id)));
    }

    @Override
    public UserDto update(Long id, UserUpdateDto dto) {
        User newUser = userMapper.updateUserDto2User(dto);
        User oldUser = userRepository.getReferenceById(id);
        newUser.setId(oldUser.getId());
        List<UserRole> userRoles = dto.getRoleIds()
                .stream()
                .map(roleRepository::getReferenceById)
                .map(role -> UserRole.builder()
                        .user(newUser)
                        .role(role)
                        .build())
                .toList();
        userRoleRepository.deleteAll(oldUser.getUserRoles());
        newUser.setUserRoles(userRoleRepository.saveAll(userRoles));
        return userMapper.user2UserDto(userRepository.save(newUser));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        user.getUserRoles().stream()
                                .map(UserRole::getRole)
                                .toList()))
                .orElseThrow(() -> new UsernameNotFoundException("User with username: %s not found!".formatted(username)));
    }
}

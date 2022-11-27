package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.UserRole;
import com.nonier.cliniccore.mapper.UserMapper;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::user2UserDto).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(user.getUsername(), user.getPassword(),
                        user.getUserRoles().stream()
                                .map(UserRole::getRole)
                                .toList()))
                .orElseThrow(() -> new UsernameNotFoundException("User with username: %s not found!".formatted(username)));
    }
}

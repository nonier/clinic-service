package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.mapper.UserMapper;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::user2UserDto)
                .toList();
    }
}

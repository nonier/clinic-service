package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.mapper.UserMapper;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.AuthService;
import com.nonier.cliniccore.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto getUserInfo(Principal principal) {
        User client = authService.getUser(principal);
        return userMapper.user2UserDto(client);
    }

    @Override
    public void updateUserInfo(Principal principal, UserUpdateDto userUpdateDto) {
        User user = authService.getUser(principal);
        user.setName(userUpdateDto.getName());
        user.setSurname(userUpdateDto.getSurname());
        user.setBirthDate(userUpdateDto.getBirthDate());
        userRepository.save(user);
    }
}
package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapperUtil {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @EncodedPasswordByRawPassword
    public String passwordByUserUpdateDto(String rawPassword) {
        return passwordEncoder.encode("{bcrypt}" + rawPassword);
    }

    @UserByUserId
    public User userByUserId(Long id) {
        return Optional.ofNullable(id)
                .map(userRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("User with id: %d not found!".formatted(id)));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface EncodedPasswordByRawPassword {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UserByUserId {

    }
}

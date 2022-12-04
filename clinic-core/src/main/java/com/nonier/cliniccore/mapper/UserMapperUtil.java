package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class UserMapperUtil {

    private final PasswordEncoder passwordEncoder;

    @PasswordByUserUpdateDto
    public String passwordByUserUpdateDto(UpdateUserDto dto) {
        return passwordEncoder.encode("{bcrypt}" + dto.getPassword());
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface PasswordByUserUpdateDto {

    }
}

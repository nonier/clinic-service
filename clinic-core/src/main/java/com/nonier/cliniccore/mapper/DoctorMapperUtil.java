package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.repository.DoctorSpecializationRepository;
import com.nonier.cliniccore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.mapstruct.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class DoctorMapperUtil {

    private final UserRepository userRepository;

    @UserByDoctorUpdateDto
    public User userByDoctorUpdateDto(DoctorUpdateDto dto) {
        return Optional.ofNullable(dto.getUserId())
                .map(userRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("User with id: %d not found!".formatted(dto.getUserId())));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UserByDoctorUpdateDto {

    }
}
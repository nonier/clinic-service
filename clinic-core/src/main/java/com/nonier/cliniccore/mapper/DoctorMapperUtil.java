package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.entity.Doctor;
import com.nonier.cliniccore.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorMapperUtil {

    private final DoctorRepository doctorRepository;

    @DoctorByDoctorId
    public Doctor doctorByDoctorId(Long id) {
        return Optional.ofNullable(id)
                .map(doctorRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: %d not found!".formatted(id)));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface DoctorByDoctorId{}
}
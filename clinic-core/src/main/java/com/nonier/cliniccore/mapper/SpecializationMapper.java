package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.SpecializationDto;
import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.entity.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecializationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    SpecializationDto specialization2SpecializationDto(Specialization specialization);

    @Mapping(target = "id", source = "specialization.id")
    @Mapping(target = "name", source = "specialization.name")
    SpecializationDto doctorSpecialization2SpecializationDto(DoctorSpecialization doctorSpecialization);
}
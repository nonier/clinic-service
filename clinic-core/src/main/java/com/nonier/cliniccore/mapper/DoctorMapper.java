package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {SpecializationMapper.class, UserMapper.class, UserMapperUtil.class, ConsultationMapper.class})
public interface DoctorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ageGroup", source = "ageGroup")
    @Mapping(target = "workExperience", source = "workExperience")
    @Mapping(target = "rank", source = "rank")
    @Mapping(target = "specializations", source = "doctorSpecializations")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "consultations", source = "consultations")
    DoctorDto doctor2DoctorDto(Doctor doctor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctorSpecializations", ignore = true)
    @Mapping(target = "consultations", ignore = true)
    @Mapping(target = "ageGroup", source = "ageGroup")
    @Mapping(target = "workExperience", source = "workExperience")
    @Mapping(target = "rank", source = "rank")
    @Mapping(target = "user", source = "userId", qualifiedBy = UserMapperUtil.UserByUserId.class)
    Doctor doctorUpdateDto2Doctor(DoctorUpdateDto dto);
}
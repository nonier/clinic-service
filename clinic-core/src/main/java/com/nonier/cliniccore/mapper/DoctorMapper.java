package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {SpecializationMapper.class, UserMapper.class, DoctorMapperUtil.class})
public interface DoctorMapper {

    @Mapping(target = "id", source = "doctor.id")
    @Mapping(target = "ageGroup", source = "doctor.ageGroup")
    @Mapping(target = "workExperience", source = "doctor.workExperience")
    @Mapping(target = "rank", source = "doctor.rank")
    @Mapping(target = "specializations", source = "doctor.doctorSpecializations")
    @Mapping(target = "user", source = "doctor.user")
    DoctorDto doctor2DoctorDto(Doctor doctor);

    @Mapping(target = "ageGroup", source = "dto.ageGroup")
    @Mapping(target = "workExperience", source = "dto.workExperience")
    @Mapping(target = "rank", source = "dto.rank")
    @Mapping(target = "user", source = "dto", qualifiedBy = DoctorMapperUtil.UserByDoctorUpdateDto.class)
    Doctor doctorUpdateDto2Doctor(DoctorUpdateDto dto);
}
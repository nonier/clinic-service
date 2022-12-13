package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.entity.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {DoctorMapper.class, UserMapper.class, UserMapperUtil.class, DoctorMapperUtil.class})
public interface ConsultationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "doctor", source = "doctor")
    @Mapping(target = "client", source = "client")
    ConsultationDto consultation2ConsultationDto(Consultation consultation);

    @Mapping(target = "date", source = "date")
    @Mapping(target = "doctor", source = "doctorId", qualifiedBy = DoctorMapperUtil.DoctorByDoctorId.class)
    @Mapping(target = "client", source = "clientId", qualifiedBy = UserMapperUtil.UserByUserId.class)
    Consultation consultationUpdateDto2Consultation(ConsultationUpdateDto dto);

}
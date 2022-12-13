package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;

import java.util.List;

public interface ConsultationService {

    List<ConsultationDto> findAll();

    List<ConsultationDto> findAllByDoctorId(Long userId);

    ConsultationDto create(ConsultationUpdateDto dto);
}
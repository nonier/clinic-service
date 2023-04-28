package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.entity.User;

import java.security.Principal;
import java.util.List;

public interface ConsultationService {

    List<ConsultationDto> findAll();

    List<ConsultationDto> findAllByDoctorId(Long userId);

    ConsultationDto create(ConsultationUpdateDto dto);

    void chooseConsultationByClient(Long consultationId, User client);

    List<ConsultationDto> findAllForClient(User user);

    List<ConsultationDto> findByDoctor(Principal principal);
}
package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.UserDto;

import java.security.Principal;
import java.util.List;

public interface ClientService {

    UserDto getClientInfo(Principal principal);

    List<ConsultationDto> findClientConsultations(Principal principal);

    void chooseConsultationByClient(Long consultationId, Principal principal);
}
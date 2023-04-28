package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.Consultation;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.mapper.UserMapper;
import com.nonier.cliniccore.repository.ConsultationRepository;
import com.nonier.cliniccore.service.AuthService;
import com.nonier.cliniccore.service.ClientService;
import com.nonier.cliniccore.service.ConsultationService;
import com.nonier.cliniccore.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final ConsultationService consultationService;
    private final MessageService messageService;


    @Override
    public UserDto getClientInfo(Principal principal) {
        User client = authService.getUser(principal);
        return userMapper.user2UserDto(client);
    }

    @Override
    public List<ConsultationDto> findClientConsultations(Principal principal) {
        User client = authService.getUser(principal);
        return consultationService.findAllForClient(client);
    }

    @Override
    public void chooseConsultationByClient(Long consultationId, Principal principal) {
        User client = authService.getUser(principal);
        consultationService.chooseConsultationByClient(consultationId, client);
    }

    @Override
    public List<MessageDto> findClientMessages(Principal principal) {
        User client = authService.getUser(principal);
        return messageService.findAllByUser(client);
    }
}
package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.entity.*;
import com.nonier.cliniccore.mapper.ConsultationMapper;
import com.nonier.cliniccore.repository.ConsultationRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.AuthService;
import com.nonier.cliniccore.service.ConsultationService;
import com.nonier.cliniccore.service.DialogService;
import com.nonier.cliniccore.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final DialogService dialogService;
    private final AuthService authService;

    @Override
    public List<ConsultationDto> findAll(Principal principal) {
        List<Consultation> consultations = new ArrayList<>();
        User user = authService.getUser(principal);
        List<String> userRoleNames = authService.getUserRoleNames(user);
        if (userRoleNames.contains("ADMIN")) {
            consultations = consultationRepository.findAll();
        } else if (userRoleNames.contains("DOCTOR")) {
            consultations = consultationRepository.findAllByDoctor_Id(user.getId());
        } else if (userRoleNames.contains("CLIENT")) {
            consultations = consultationRepository.findAllByClient_id(user.getId());
        }
        return consultations
                .stream()
                .map(consultationMapper::consultation2ConsultationDto)
                .toList();
    }

    @Override
    public List<ConsultationDto> findAllByDoctorId(Long doctorId) {
        return consultationRepository.findAllByDoctor_Id(doctorId)
                .stream()
                .map(consultationMapper::consultation2ConsultationDto)
                .toList();
    }

    @Override
    public ConsultationDto create(ConsultationUpdateDto dto) {
        return consultationMapper.consultation2ConsultationDto(consultationRepository.save(
                consultationMapper.consultationUpdateDto2Consultation(dto)
        ));
    }

    @Override
    public void chooseConsultation(Long consultationId, Principal principal) {
        User user = authService.getUser(principal);
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consultation with id: %d not found!".formatted(consultationId)));
        consultation.setClient(user);
        Dialog dialog = dialogService.createByUsers(List.of(consultation.getDoctor().getId(), user.getId()));
        consultation.setDialog(dialog);
        consultationRepository.save(consultation);
    }
}
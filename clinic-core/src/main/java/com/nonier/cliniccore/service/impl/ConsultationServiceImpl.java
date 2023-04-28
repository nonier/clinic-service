package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.entity.Consultation;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.User;
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
    public List<ConsultationDto> findAll() {
        return consultationRepository.findAll()
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
    public void chooseConsultationByClient(Long consultationId, User client) {
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consultation with id: %d not found!".formatted(consultationId)));
        consultation.setClient(client);
        Dialog dialog = dialogService.createByUsers(List.of(consultation.getDoctor().getId(), client.getId()));
        consultation.setDialog(dialog);
        consultationRepository.save(consultation);
    }

    @Override
    public List<ConsultationDto> findAllForClient(User user) {
        return consultationRepository.findAllByClient_id(user.getId())
                .stream()
                .map(consultationMapper::consultation2ConsultationDto)
                .toList();
    }

    @Override
    public List<ConsultationDto> findByDoctor(Principal principal) {
        User doctor = authService.getUser(principal);
        return consultationRepository.findAllByDoctor_Id(doctor.getId())
                .stream()
                .map(consultationMapper::consultation2ConsultationDto)
                .toList();
    }
}
package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.entity.Consultation;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.jwt.JwtAuthentication;
import com.nonier.cliniccore.mapper.ConsultationMapper;
import com.nonier.cliniccore.repository.ConsultationRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.ConsultationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserRepository userRepository;

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
    public void chooseConsultationByClient(Long consultationId, Principal principal) {
        User user = userRepository.findByUsername(((JwtAuthentication) principal).getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(principal.getName())));
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consultation with id: %d not found!".formatted(consultationId)));
        consultation.setClient(user);
        consultationRepository.save(consultation);
    }

    @Override
    public List<ConsultationDto> findAllForClient(Principal principal) {
        User user = userRepository.findByUsername(((JwtAuthentication) principal).getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User with username: %s not found!".formatted(principal.getName())));
        return consultationRepository.findAllByClient_id(user.getId())
                .stream()
                .map(consultationMapper::consultation2ConsultationDto)
                .toList();
    }
}
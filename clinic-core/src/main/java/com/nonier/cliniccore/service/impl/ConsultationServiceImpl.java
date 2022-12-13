package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.mapper.ConsultationMapper;
import com.nonier.cliniccore.repository.ConsultationRepository;
import com.nonier.cliniccore.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

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
}
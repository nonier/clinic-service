package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.SpecializationDto;
import com.nonier.cliniccore.mapper.SpecializationMapper;
import com.nonier.cliniccore.repository.SpecializationRepository;
import com.nonier.cliniccore.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;


    @Override
    public List<SpecializationDto> findAll() {
        return specializationRepository.findAll()
                .stream()
                .map(specializationMapper::specialization2SpecializationDto)
                .toList();
    }
}

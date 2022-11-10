package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.mapper.DoctorMapper;
import com.nonier.cliniccore.repository.DoctorRepository;
import com.nonier.cliniccore.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDto> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::doctor2DoctorDto)
                .toList();
    }
}
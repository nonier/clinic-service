package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> findAll();

    DoctorDto findById(Long id);

    DoctorDto create(DoctorUpdateDto dto);

    DoctorDto update(Long id, DoctorUpdateDto dto);

    void delete(Long id);
}
package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> findAll();
}
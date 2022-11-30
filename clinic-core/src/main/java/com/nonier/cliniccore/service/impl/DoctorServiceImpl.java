package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.entity.Doctor;
import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.entity.DoctorSpecializationId;
import com.nonier.cliniccore.mapper.DoctorMapper;
import com.nonier.cliniccore.repository.DoctorRepository;
import com.nonier.cliniccore.repository.DoctorSpecializationRepository;
import com.nonier.cliniccore.repository.SpecializationRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SpecializationRepository specializationRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final UserRepository userRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper, SpecializationRepository specializationRepository, DoctorSpecializationRepository doctorSpecializationRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.specializationRepository = specializationRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DoctorDto> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::doctor2DoctorDto)
                .toList();
    }

    @Override
    public DoctorDto findById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::doctor2DoctorDto)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: %d not found!".formatted(id)));
    }

    @Override
    public DoctorDto create(DoctorUpdateDto dto) {
        Doctor doctor = doctorMapper.doctorUpdateDto2Doctor(dto);
        doctorRepository.save(doctor);
        List<DoctorSpecialization> doctorSpecializations = dto.getSpecializationIds()
                .stream()
                .map(specializationRepository::getReferenceById)
                .map(specialization -> DoctorSpecialization.builder()
                        .doctor(doctor)
                        .specialization(specialization)
                        .build())
                .peek(doctorSpecializationRepository::save)
                .toList();
        doctor.setDoctorSpecializations(doctorSpecializations);
        return doctorMapper.doctor2DoctorDto(doctor);
    }

    @Override
    public DoctorDto update(Long doctorId, DoctorUpdateDto dto) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: %d not found!".formatted(doctorId)));
        doctor.setRank(dto.getRank());
        doctor.setWorkExperience(dto.getWorkExperience());
        doctor.setAgeGroup(dto.getAgeGroup());
        doctor.setUser(userRepository.getReferenceById(dto.getUserId()));
        List<DoctorSpecialization> doctorSpecializations = dto.getSpecializationIds()
                .stream()
                .map(specId -> doctorSpecializationRepository.getReferenceById(new DoctorSpecializationId(doctorId, specId)))
                .toList();
        doctor.setDoctorSpecializations(doctorSpecializations);
        doctorRepository.save(doctor);
        return doctorMapper.doctor2DoctorDto(doctor);
    }
}
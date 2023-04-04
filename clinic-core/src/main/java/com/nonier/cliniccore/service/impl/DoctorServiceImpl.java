package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.entity.Doctor;
import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.mapper.DoctorMapper;
import com.nonier.cliniccore.repository.DoctorRepository;
import com.nonier.cliniccore.repository.DoctorSpecializationRepository;
import com.nonier.cliniccore.repository.SpecializationRepository;
import com.nonier.cliniccore.service.DoctorService;
import com.nonier.cliniccore.specification.DoctorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SpecializationRepository specializationRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    @Override
    public List<DoctorDto> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::doctor2DoctorDto)
                .toList();
    }

    @Override
    public DoctorDto findById(Long id) {
        return doctorMapper.doctor2DoctorDto(doctorRepository.getReferenceById(id));
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
                .toList();
        doctor.setDoctorSpecializations(doctorSpecializationRepository.saveAll(doctorSpecializations));
        return doctorMapper.doctor2DoctorDto(doctor);
    }

    @Override
    public DoctorDto update(Long id, DoctorUpdateDto dto) {
        Doctor newDoctor = doctorMapper.doctorUpdateDto2Doctor(dto);
        Doctor oldDoctor = doctorRepository.getReferenceById(id);
        newDoctor.setId(oldDoctor.getId());
        List<DoctorSpecialization> doctorSpecializations = dto.getSpecializationIds()
                .stream()
                .map(specializationRepository::getReferenceById)
                .map(specialization -> DoctorSpecialization.builder()
                        .doctor(newDoctor)
                        .specialization(specialization)
                        .build())
                .toList();
        doctorSpecializationRepository.deleteAll(oldDoctor.getDoctorSpecializations());
        newDoctor.setDoctorSpecializations(doctorSpecializationRepository.saveAll(doctorSpecializations));
        return doctorMapper.doctor2DoctorDto(doctorRepository.save(newDoctor));
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<DoctorDto> findAllByFilter(Optional<String> name) {
        DoctorSpecification doctorSpecification = new DoctorSpecification(name);
        return doctorRepository.findAll(doctorSpecification)
                .stream()
                .map(doctorMapper::doctor2DoctorDto)
                .toList();
    }
}
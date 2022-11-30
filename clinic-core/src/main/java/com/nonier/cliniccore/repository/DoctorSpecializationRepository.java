package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.entity.DoctorSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, DoctorSpecializationId> {
}

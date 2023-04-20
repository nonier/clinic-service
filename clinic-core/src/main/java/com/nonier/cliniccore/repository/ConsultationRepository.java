package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    List<Consultation> findAllByDoctor_Id(Long doctorId);

    List<Consultation> findAllByClient_id(Long clientId);
}

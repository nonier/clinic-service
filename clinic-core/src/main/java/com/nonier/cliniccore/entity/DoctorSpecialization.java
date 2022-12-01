package com.nonier.cliniccore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_specialization")
public class DoctorSpecialization {

    @EmbeddedId
    @Builder.Default
    private DoctorSpecializationId id = new DoctorSpecializationId();

    @MapsId("doctorId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @MapsId("specializationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;
}
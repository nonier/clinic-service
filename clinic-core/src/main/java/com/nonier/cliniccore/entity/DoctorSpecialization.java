package com.nonier.cliniccore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor_specialization")
public class DoctorSpecialization {

    @EmbeddedId
    private DoctorSpecializationId id;

    @MapsId("doctorId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @MapsId("specializationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;
}
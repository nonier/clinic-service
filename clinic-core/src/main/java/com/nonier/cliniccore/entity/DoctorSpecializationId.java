package com.nonier.cliniccore.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class DoctorSpecializationId implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "specialization_id")
    private Long specializationId;
}
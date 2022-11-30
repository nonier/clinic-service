package com.nonier.cliniccore.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSpecializationId implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "specialization_id")
    private Long specializationId;
}
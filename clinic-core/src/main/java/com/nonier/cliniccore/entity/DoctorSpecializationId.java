package com.nonier.cliniccore.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
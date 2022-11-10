package com.nonier.cliniccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "doctorSpecializations")
@ToString(exclude = "doctorSpecializations")
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group", nullable = false)
    private AgeGroup ageGroup;

    @Column(name = "work_experience")
    private Integer workExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank", nullable = false)
    private Rank rank;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorSpecialization> doctorSpecializations = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
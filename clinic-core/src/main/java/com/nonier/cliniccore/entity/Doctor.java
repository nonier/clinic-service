package com.nonier.cliniccore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<DoctorSpecialization> doctorSpecializations = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Consultation> consultations = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
package com.nonier.cliniccore.dto;

import com.nonier.cliniccore.entity.AgeGroup;
import com.nonier.cliniccore.entity.Rank;
import lombok.Data;

import java.util.List;

@Data
public class DoctorUpdateDto {

    private AgeGroup ageGroup;
    private Integer workExperience;
    private Rank rank;
    private List<Long> specializationIds;
    private Long userId;
}

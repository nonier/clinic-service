package com.nonier.cliniccore.dto;

import com.nonier.cliniccore.entity.AgeGroup;
import com.nonier.cliniccore.entity.Rank;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DoctorUpdateDto {

    @NotNull
    private AgeGroup ageGroup;
    private Integer workExperience;
    @NotNull
    private Rank rank;
    private List<Long> specializationIds;
    @NotNull
    private Long userId;
}

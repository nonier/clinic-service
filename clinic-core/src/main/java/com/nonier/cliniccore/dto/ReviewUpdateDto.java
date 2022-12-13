package com.nonier.cliniccore.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewUpdateDto {

    @Max(5)
    @Min(1)
    private Short rate;
    private String comment;
    @NotNull
    private Long userId;
}

package com.nonier.cliniccore.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DialogUpdateDto {

    @Size(min = 2)
    private List<Long> userIds;
}

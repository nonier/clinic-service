package com.nonier.cliniccore.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultationUpdateDto {

    @Future
    private LocalDateTime date;
    @NotNull
    private Long doctorId;
    private Long clientId;
}

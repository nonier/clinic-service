package com.nonier.cliniccore.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultationDto {

    private Long id;
    private LocalDateTime date;
    private UserDto client;
}

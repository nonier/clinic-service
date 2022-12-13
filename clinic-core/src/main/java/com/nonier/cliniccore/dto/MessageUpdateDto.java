package com.nonier.cliniccore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageUpdateDto {

    private Long id;
    @NotBlank
    private String text;
    @NotNull
    private Long userFromId;
    @NotNull
    private Long dialogId;
}

package com.nonier.cliniccore.dto;

import lombok.Data;

@Data
public class MessageUpdateDto {

    private Long id;
    private String text;
    private Long userFromId;
    private Long dialogId;
}

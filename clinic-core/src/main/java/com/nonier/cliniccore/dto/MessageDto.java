package com.nonier.cliniccore.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private Long id;
    private String text;
    private LocalDateTime creationDate;
    private Long dialogId;
    private UserDto userFrom;
}

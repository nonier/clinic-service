package com.nonier.cliniccore.dto;

import lombok.Data;

@Data
public class MessageDto {

    private Long id;
    private String text;
    private UserDto userFrom;
}

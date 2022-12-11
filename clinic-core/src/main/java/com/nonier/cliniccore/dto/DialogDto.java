package com.nonier.cliniccore.dto;

import lombok.Data;

import java.util.List;

@Data
public class DialogDto {

    private Long id;
    private List<UserDto> users;
    private List<MessageDto> messages;
}

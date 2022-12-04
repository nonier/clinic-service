package com.nonier.cliniccore.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDto {

    private String username;
    private String name;
    private String surname;
    private String password;
    private List<Long> roleIds;
}

package com.nonier.cliniccore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateDto {

    @NotBlank
    private String username;
    private String name;
    private String surname;
    @NotBlank
    private String password;
    @NotEmpty
    private List<Long> roleIds;
}

package com.nonier.cliniccore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

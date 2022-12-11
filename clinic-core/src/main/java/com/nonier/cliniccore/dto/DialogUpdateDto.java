package com.nonier.cliniccore.dto;

import lombok.Data;

import java.util.List;

@Data
public class DialogUpdateDto {

    private List<Long> userIds;
}

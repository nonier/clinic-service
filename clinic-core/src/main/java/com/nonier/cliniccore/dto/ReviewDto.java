package com.nonier.cliniccore.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Short id;
    private Short rate;
    private String comment;
    private UserDto user;
}

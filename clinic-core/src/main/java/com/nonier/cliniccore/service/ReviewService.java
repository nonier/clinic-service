package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAll();
}

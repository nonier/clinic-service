package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAll();

    List<ReviewDto> findAllByUserId(Long userId);

    ReviewDto create(ReviewUpdateDto dto);
}

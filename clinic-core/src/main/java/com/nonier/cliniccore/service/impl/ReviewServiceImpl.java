package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.mapper.ReviewMapper;
import com.nonier.cliniccore.repository.ReviewRepository;
import com.nonier.cliniccore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::review2ReviewDto)
                .toList();
    }
}

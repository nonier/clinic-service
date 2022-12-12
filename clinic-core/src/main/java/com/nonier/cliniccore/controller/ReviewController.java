package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }
}

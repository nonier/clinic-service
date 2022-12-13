package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.dto.ReviewUpdateDto;
import com.nonier.cliniccore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReviewDto>> findAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ReviewDto> create(@Validated @RequestBody ReviewUpdateDto dto) {
        return ResponseEntity.ok(reviewService.create(dto));
    }
}

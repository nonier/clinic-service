package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.SpecializationDto;
import com.nonier.cliniccore.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<SpecializationDto>> findAll() {
        return ResponseEntity.ok(specializationService.findAll());
    }
}

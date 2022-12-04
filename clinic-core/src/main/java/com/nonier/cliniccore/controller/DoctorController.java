package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.DoctorDto;
import com.nonier.cliniccore.dto.DoctorUpdateDto;
import com.nonier.cliniccore.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> findAll(){
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorDto> create(@Validated @RequestBody DoctorUpdateDto dto) {
        return ResponseEntity.ok(doctorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id,
                                            @Validated @RequestBody DoctorUpdateDto dto) {
        return ResponseEntity.ok(doctorService.update(id, dto));
    }
}
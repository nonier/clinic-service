package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.ConsultationUpdateDto;
import com.nonier.cliniccore.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> findAll(Principal principal) {
        return ResponseEntity.ok(consultationService.findAll(principal));
    }

    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<List<ConsultationDto>> findAllByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(consultationService.findAllByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> create(@Validated @RequestBody ConsultationUpdateDto dto) {
        return ResponseEntity.ok(consultationService.create(dto));
    }

    @PatchMapping("/{consultationId}/choose")
    public ResponseEntity<Void> chooseConsultation(
            @PathVariable Long consultationId, Principal principal) {
        consultationService.chooseConsultation(consultationId, principal);
        return ResponseEntity.ok().build();
    }
}
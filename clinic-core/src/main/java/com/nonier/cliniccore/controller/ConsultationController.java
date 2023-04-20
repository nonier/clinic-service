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
    public ResponseEntity<List<ConsultationDto>> findAll() {
        return ResponseEntity.ok(consultationService.findAll());
    }

    /**
     * Возможно стоит сделать отдельный контроллер для клиентов вместо этого
     */
    @GetMapping("/client")
    public ResponseEntity<List<ConsultationDto>> findAllForClient(Principal principal) {
        List<ConsultationDto> result = consultationService.findAllForClient(principal);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<List<ConsultationDto>> findAllByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(consultationService.findAllByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> create(@Validated @RequestBody ConsultationUpdateDto dto) {
        return ResponseEntity.ok(consultationService.create(dto));
    }

    @PutMapping("/{consultationId}")
    public ResponseEntity<Void> chooseConsultationByClient(
            @PathVariable Long consultationId,
            Principal principal) {
        consultationService.chooseConsultationByClient(consultationId, principal);
        return ResponseEntity.ok().build();
    }

}

package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.ConsultationDto;
import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<UserDto> getClientInfo(Principal principal) {
        return ResponseEntity.ok(clientService.getClientInfo(principal));
    }

    @GetMapping("/consultations")
    public ResponseEntity<List<ConsultationDto>> findClientConsultations(Principal principal) {
        return ResponseEntity.ok(clientService.findClientConsultations(principal));
    }

    @PutMapping("/consultations/{consultationId}")
    public ResponseEntity<Void> chooseConsultationByClient(
            @PathVariable Long consultationId,
            Principal principal) {
        clientService.chooseConsultationByClient(consultationId, principal);
        return ResponseEntity.ok().build();
    }
}

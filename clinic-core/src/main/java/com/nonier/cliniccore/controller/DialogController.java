package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.service.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dialogs")
public class DialogController {

    private final DialogService dialogService;

    @GetMapping
    public ResponseEntity<Page<DialogDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(dialogService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<DialogDto> create(@Validated @RequestBody DialogUpdateDto dto) {
        return ResponseEntity.ok(dialogService.create(dto));
    }
}

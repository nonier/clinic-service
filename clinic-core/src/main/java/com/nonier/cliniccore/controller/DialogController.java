package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.service.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dialogs")
public class DialogController {

    private final DialogService dialogService;

    @GetMapping
    public ResponseEntity<Page<DialogDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(dialogService.findAll(pageable));
    }

    @GetMapping("/{dialogId}/messages")
    public ResponseEntity<List<MessageDto>> findMessagesInDialog(
            @PathVariable Long dialogId) {
        return ResponseEntity.ok(dialogService.findMessagesInDialog(dialogId));
    }

    @PostMapping
    public ResponseEntity<DialogDto> create(@Validated @RequestBody DialogUpdateDto dto) {
        return ResponseEntity.ok(dialogService.create(dto));
    }
}

package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    public final MessageService messageService;

    @GetMapping
    public ResponseEntity<Page<MessageDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(messageService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<MessageDto> create(@Validated @RequestBody MessageUpdateDto dto) {
        return ResponseEntity.ok(messageService.create(dto));
    }
}

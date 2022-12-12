package com.nonier.cliniccore.controller;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<MessageDto> create(MessageUpdateDto dto) {
        return ResponseEntity.ok(messageService.create(dto));
    }
}

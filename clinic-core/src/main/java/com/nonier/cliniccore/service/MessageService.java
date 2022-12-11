package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    Page<MessageDto> findAll(Pageable pageable);

    MessageDto create(MessageUpdateDto dto);
}

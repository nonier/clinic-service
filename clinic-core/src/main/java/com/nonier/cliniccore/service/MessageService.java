package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    Page<MessageDto> findAll(Pageable pageable);

    MessageDto create(MessageUpdateDto dto);

    List<MessageDto> findAllByUser(User user);
}

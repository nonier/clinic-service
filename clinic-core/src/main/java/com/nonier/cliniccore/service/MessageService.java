package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface MessageService {

    List<MessageDto> findAll(Principal principal);

    MessageDto create(MessageUpdateDto dto);
}

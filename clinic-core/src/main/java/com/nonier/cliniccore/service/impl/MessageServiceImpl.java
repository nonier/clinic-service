package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.mapper.MessageMapper;
import com.nonier.cliniccore.repository.MessageRepository;
import com.nonier.cliniccore.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    @Override
    public Page<MessageDto> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable)
                .map(messageMapper::message2MessageDto);
    }

    @Override
    public MessageDto create(MessageUpdateDto dto) {
        return messageMapper.message2MessageDto(messageRepository.save(messageMapper.messageUpdateDto2Message(dto)));
    }
}

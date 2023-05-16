package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.entity.Consultation;
import com.nonier.cliniccore.entity.Message;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.mapper.MessageMapper;
import com.nonier.cliniccore.repository.MessageRepository;
import com.nonier.cliniccore.service.AuthService;
import com.nonier.cliniccore.service.MessageService;
import com.nonier.cliniccore.service.NotificationService;
import com.nonier.cliniccore.stomp.Notification;
import com.nonier.cliniccore.stomp.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final NotificationService notificationService;
    private final AuthService authService;

    @Override
    public List<MessageDto> findAll(Principal principal) {
        return messageRepository.findAllByUser(authService.getUser(principal))
                .stream()
                .map(messageMapper::message2MessageDto)
                .toList();
    }



    @Override
    public MessageDto create(MessageUpdateDto dto) {
        Message message = messageRepository.save(messageMapper.messageUpdateDto2Message(dto));
        MessageDto messageDto = messageMapper.message2MessageDto(message);
        notificationService.sendNotificationToUsers(new Notification<>(NotificationType.NEW_MESSAGE, messageDto),
                message.getDialog());
        return messageDto;
    }
}

package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.entity.UserDialog;
import com.nonier.cliniccore.mapper.DialogMapper;
import com.nonier.cliniccore.mapper.MessageMapper;
import com.nonier.cliniccore.repository.DialogRepository;
import com.nonier.cliniccore.repository.MessageRepository;
import com.nonier.cliniccore.repository.UserDialogRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.DialogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {

    private final DialogRepository dialogRepository;
    private final DialogMapper dialogMapper;
    private final UserRepository userRepository;
    private final UserDialogRepository userDialogRepository;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @Override
    public Page<DialogDto> findAll(Pageable pageable) {
        return dialogRepository.findAll(pageable)
                .map(dialogMapper::dialog2DialogDto);
    }

    @Override
    public DialogDto create(DialogUpdateDto dto) {
        Dialog dialog = createByUsers(dto.getUserIds());
        return dialogMapper.dialog2DialogDto(dialog);
    }

    @Override
    public Dialog createByUsers(List<Long> userIds) {
        List<User> users = userIds
                .stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("User with id: %d not found!".formatted(id))))
                .toList();
        Dialog dialog = dialogRepository.save(new Dialog());
        List<UserDialog> userDialogs = users.stream()
                .map(user -> UserDialog.builder()
                        .dialog(dialog)
                        .user(user)
                        .build())
                .toList();
        dialog.setUserDialogs(userDialogRepository.saveAll(userDialogs));
        return dialogRepository.save(dialog);
    }

    @Override
    public List<MessageDto> findMessagesInDialog(Long dialogId) {
        return messageRepository.findAllByDialog_Id(dialogId)
                .stream()
                .map(messageMapper::message2MessageDto)
                .toList();
    }
}
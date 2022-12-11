package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.entity.UserDialog;
import com.nonier.cliniccore.mapper.DialogMapper;
import com.nonier.cliniccore.repository.DialogRepository;
import com.nonier.cliniccore.repository.UserDialogRepository;
import com.nonier.cliniccore.repository.UserRepository;
import com.nonier.cliniccore.service.DialogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {

    private final DialogRepository dialogRepository;
    private final DialogMapper dialogMapper;
    private final UserRepository userRepository;
    private final UserDialogRepository userDialogRepository;

    @Override
    public Page<DialogDto> findAll(Pageable pageable) {
        return dialogRepository.findAll(pageable)
                .map(dialogMapper::dialog2DialogDto);
    }

    @Override
    public DialogDto create(DialogUpdateDto dto) {
        List<User> users = dto.getUserIds()
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
        return dialogMapper.dialog2DialogDto(dialog);
    }
}
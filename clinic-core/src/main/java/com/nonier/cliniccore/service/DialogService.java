package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import com.nonier.cliniccore.entity.Dialog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DialogService {

    Page<DialogDto> findAll(Pageable pageable);

    DialogDto create(DialogUpdateDto dto);

    Dialog createByUsers(List<Long> userIds);
}

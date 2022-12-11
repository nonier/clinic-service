package com.nonier.cliniccore.service;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.dto.DialogUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DialogService {

    Page<DialogDto> findAll(Pageable pageable);

    DialogDto create(DialogUpdateDto dto);
}

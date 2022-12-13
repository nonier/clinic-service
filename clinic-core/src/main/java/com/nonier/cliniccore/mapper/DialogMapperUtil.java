package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.UserDialog;
import com.nonier.cliniccore.repository.DialogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DialogMapperUtil {

    private final UserMapper userMapper;
    private final DialogRepository dialogRepository;

    @UsersByUserDialogs
    public List<UserDto> usersByDialog(List<UserDialog> userDialogs) {
        return userDialogs
                .stream()
                .map(UserDialog::getUser)
                .map(userMapper::user2UserDto)
                .toList();
    }

    @DialogByDialogId
    public Dialog dialogByDialogId(Long id) {
        return Optional.ofNullable(id)
                .map(dialogRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("Dialog with id: %d not found!".formatted(id)));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UsersByUserDialogs {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface DialogByDialogId {

    }
}

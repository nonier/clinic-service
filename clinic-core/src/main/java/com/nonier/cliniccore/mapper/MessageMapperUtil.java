package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.repository.DialogRepository;
import com.nonier.cliniccore.repository.DoctorRepository;
import com.nonier.cliniccore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessageMapperUtil {

    private final UserRepository userRepository;
    private final DialogRepository dialogRepository;

    @UserByUserFromId
    public User userByUserFromId(MessageUpdateDto dto) {
        return Optional.ofNullable(dto.getUserFromId())
                .map(userRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("User with id: %d not found!".formatted(dto.getUserFromId())));
    }

    @DialogByDialogId
    public Dialog dialogByDialogId (MessageUpdateDto dto) {
        return Optional.ofNullable(dto.getDialogId())
                .map(dialogRepository::getReferenceById)
                .orElseThrow(() -> new EntityNotFoundException("Dialog with id: %d not found!".formatted(dto.getDialogId())));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UserByUserFromId {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface  DialogByDialogId {

    }
}

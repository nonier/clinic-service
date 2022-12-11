package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.UserDialog;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DialogMapperUtil {

    private final UserMapper userMapper;

    @UsersByDialog
    public List<UserDto> usersByDialog(Dialog dialog) {
        return dialog.getUserDialogs()
                .stream()
                .map(UserDialog::getUser)
                .map(userMapper::user2UserDto)
                .toList();
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UsersByDialog {

    }
}

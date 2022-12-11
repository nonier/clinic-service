package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.DialogDto;
import com.nonier.cliniccore.entity.Dialog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {DialogMapperUtil.class, MessageMapper.class}
)
public interface DialogMapper {

    @Mapping(target = "id", source = "dialog.id")
    @Mapping(target = "users", source = "dialog", qualifiedBy = DialogMapperUtil.UsersByDialog.class)
    @Mapping(target = "messages", source = "messages")
    DialogDto dialog2DialogDto(Dialog dialog);
}

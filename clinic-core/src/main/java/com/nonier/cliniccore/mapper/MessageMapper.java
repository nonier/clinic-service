package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.MessageDto;
import com.nonier.cliniccore.dto.MessageUpdateDto;
import com.nonier.cliniccore.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, DialogMapperUtil.class, UserMapperUtil.class})
public interface MessageMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "creationDate", source = "creationDate")
    @Mapping(target = "dialogId", source = "dialog.id")
    @Mapping(target = "userFrom", source = "userFrom")
    MessageDto message2MessageDto(Message message);

    @Mapping(target = "text", source = "text")
    @Mapping(target = "userFrom", source = "userFromId", qualifiedBy = UserMapperUtil.UserByUserId.class)
    @Mapping(target = "dialog", source = "dialogId", qualifiedBy = DialogMapperUtil.DialogByDialogId.class)
    Message messageUpdateDto2Message(MessageUpdateDto dto);
}

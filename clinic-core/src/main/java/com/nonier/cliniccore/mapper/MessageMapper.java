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

    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "text", source = "message.text")
    @Mapping(target = "userFrom", source = "message.userFrom")
    MessageDto message2MessageDto(Message message);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "text", source = "dto.text")
    @Mapping(target = "userFrom", source = "dto.userFromId", qualifiedBy = UserMapperUtil.UserByUserId.class)
    @Mapping(target = "dialog", source = "dto.dialogId", qualifiedBy = DialogMapperUtil.DialogByDialogId.class)
    Message messageUpdateDto2Message(MessageUpdateDto dto);
}

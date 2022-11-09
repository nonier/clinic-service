package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleMapper.class})
public interface UserMapper {


    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "roleDtos", source = "user.userRoles")
    UserDto user2UserDto(User user);
}

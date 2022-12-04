package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UpdateUserDto;
import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleMapper.class, UserMapperUtil.class})
public interface UserMapper {


    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "roleDtos", source = "user.userRoles")
    UserDto user2UserDto(User user);

    @Mapping(target = "username", source = "dto.username")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "surname", source = "dto.surname")
    @Mapping(target = "password", source = "dto", qualifiedBy = UserMapperUtil.PasswordByUserUpdateDto.class)
    User updateUserDto2User(UpdateUserDto dto);
}

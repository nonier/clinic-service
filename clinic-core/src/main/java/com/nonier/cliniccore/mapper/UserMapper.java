package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.UserUpdateDto;
import com.nonier.cliniccore.dto.UserDto;
import com.nonier.cliniccore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleMapper.class, UserMapperUtil.class})
public interface UserMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "roleDtos", source = "userRoles")
    UserDto user2UserDto(User user);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "password", source = "password", qualifiedBy = UserMapperUtil.EncodedPasswordByRawPassword.class)
    User updateUserDto2User(UserUpdateDto dto);
}

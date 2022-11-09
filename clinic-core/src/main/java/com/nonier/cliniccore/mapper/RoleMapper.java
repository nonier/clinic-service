package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.RoleDto;
import com.nonier.cliniccore.entity.Role;
import com.nonier.cliniccore.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    @Mapping(target = "id", source = "role.id")
    @Mapping(target = "name", source = "role.name")
    RoleDto role2RoleDto(Role role);

    @Mapping(target = "id", source = "userRole.role.id")
    @Mapping(target = "name", source = "userRole.role.name")
    RoleDto userRole2RoleDto(UserRole userRole);
}

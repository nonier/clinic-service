package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.dto.ReviewUpdateDto;
import com.nonier.cliniccore.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {UserMapperUtil.class, UserMapper.class, UserMapperUtil.class})
public interface ReviewMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "user", source = "user")
    ReviewDto review2ReviewDto(Review review);

    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "user", source = "userId", qualifiedBy = UserMapperUtil.UserByUserId.class)
    Review reviewUpdateDto2Review(ReviewUpdateDto dto);
}

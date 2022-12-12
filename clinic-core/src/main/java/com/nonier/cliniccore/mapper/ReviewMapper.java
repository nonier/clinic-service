package com.nonier.cliniccore.mapper;

import com.nonier.cliniccore.dto.ReviewDto;
import com.nonier.cliniccore.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
uses = {UserMapperUtil.class, UserMapper.class})
public interface ReviewMapper {

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "rate", source = "review.rate")
    @Mapping(target = "comment", source = "review.comment")
    @Mapping(target = "user", source = "review.user")
    ReviewDto review2ReviewDto(Review review);
}

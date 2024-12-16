package com.carlos.blog_api.mapper;

import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
        )
public interface UserMapper {

    UserDTO convertToDTO(UserEntity user);


    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserEntity convertToEntity(UserDTO userDTO);
}

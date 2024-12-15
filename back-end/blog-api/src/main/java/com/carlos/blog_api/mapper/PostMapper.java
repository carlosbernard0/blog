package com.carlos.blog_api.mapper;

import com.carlos.blog_api.dto.PostDTO;
import com.carlos.blog_api.dto.PostDTOCreate;
import com.carlos.blog_api.dto.UserDTO;
import com.carlos.blog_api.entity.PostEntity;
import com.carlos.blog_api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
        )
public interface PostMapper {


    PostDTO convertToDTO(PostEntity post);

    @Mapping(target = "user", ignore = true)
    PostEntity convertToEntity(PostDTO postDTO);

    @Mapping(target = "idPost", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "statusPost", ignore = true)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "user", ignore = true)
    PostEntity convertToEntityCreate(PostDTOCreate postDTO);

    PostDTOCreate convertToDTOCreate(PostEntity post);

}

package com.carlos.blog_api.mapper;

import com.carlos.blog_api.dto.CommentDTO;
import com.carlos.blog_api.dto.CommentDTOCreateUpdate;
import com.carlos.blog_api.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
        )
public interface CommentMapper {


    CommentDTO convertToDTO(CommentEntity comment);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    CommentEntity convertToEntity(CommentDTO commentDTO);

    @Mapping(target = "idComment", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    CommentEntity convertToEntityCreate(CommentDTOCreateUpdate commentDTOCreateUpdate);

    CommentDTOCreateUpdate convertToDTOCreate(CommentEntity comment);


}

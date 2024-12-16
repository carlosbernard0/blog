package com.carlos.blog_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTOCreateUpdate {

    @JsonProperty("contentComment")
    @Schema(description = "conteudo do comentario", example = "muito bacana, adorei!")
    @NotEmpty
    private String contentComment;

    @JsonProperty("idUser")
    @Schema(description = "Id do usuario do comentario", example = "1")
    private Integer idUser;

    @JsonProperty("idPost")
    @Schema(description = "Id do post do comentario", example = "1")
    private Integer idPost;

}

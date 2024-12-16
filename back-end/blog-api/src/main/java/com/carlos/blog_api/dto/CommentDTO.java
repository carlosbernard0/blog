package com.carlos.blog_api.dto;

import com.carlos.blog_api.enums.PostStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @JsonProperty("idComment")
    @Schema(description = "codigo indentificador do comentario", example = "1")
    private Integer idComment;

    @JsonProperty("contentComment")
    @Schema(description = "conteudo do comentario", example = "muito bacana, adorei!")
    @NotEmpty
    private String contentComment;

    @JsonProperty("createDate")
    @Schema(description = "Data de criacao do comentario")
    @NotEmpty
    private Date createDate;

    @JsonProperty("updateDate")
    @Schema(description = "Data de atualizacao do comentario")
    private Date updateDate;


    @JsonProperty("idUser")
    @Schema(description = "Id do usuario do comentario", example = "1")
    private Integer idUser;

    @JsonProperty("idPost")
    @Schema(description = "Id do post do comentario", example = "1")
    private Integer idPost;

}

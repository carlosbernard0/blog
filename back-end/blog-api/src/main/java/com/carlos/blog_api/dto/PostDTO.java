package com.carlos.blog_api.dto;

import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.enums.PostStatus;
import com.carlos.blog_api.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @JsonProperty("idPost")
    @Schema(description = "codigo indentificador do post", example = "1")
    private Integer idPost;

    @JsonProperty("titlePost")
    @Schema(description = "titulo do post", example = "Comunição Efetiva")
    @NotEmpty
    private String titlePost;

    @JsonProperty("contentPost")
    @Schema(description = "conteudo do post", example = "Lorem Ipsum is simply dummy text of th" +
            "e printing and typesetting industry. Lorem Ipsum has been the industry's standard" +
            " dummy text ever since the 1500s, when an unknown printer took a galley of type and" +
            " scrambled it to make a type specimen book")
    @NotEmpty
    private String contentPost;

    @JsonProperty("createDate")
    @Schema(description = "Data de criacao do post")
    @NotEmpty
    private Date createDate;

    @JsonProperty("updateDate")
    @Schema(description = "Data de atualizacao do post")
    private Date updateDate;

    @JsonProperty("statusPost")
    @Schema(description = "Status do post", example = "PENDING")
    @NotEmpty
    private PostStatus statusPost;

    @JsonProperty("idUser")
    @Schema(description = "Id do usuario do post", example = "1")
    private Integer idUser;

}

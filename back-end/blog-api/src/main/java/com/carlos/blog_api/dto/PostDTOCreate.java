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
public class PostDTOCreate {

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


}

package com.carlos.blog_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterDTO {
    @JsonProperty("email")
    @Schema(description = "Email do usuario", example = "carlos@gmail.com")
    @NotEmpty
    private String email;

    @JsonProperty("username")
    @Schema(description = "login do usuario", example = "carlosbernardo")
    @NotEmpty
    private String username;

    @JsonProperty("password")
    @Schema(description = "senha do usuario", example = "carlos123")
    @NotEmpty
    private String password;
}

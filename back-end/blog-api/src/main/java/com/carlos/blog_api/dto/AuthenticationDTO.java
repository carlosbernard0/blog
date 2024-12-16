package com.carlos.blog_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationDTO {
    @JsonProperty("login")
    @Schema(description = "login do usuario", example = "Carlos")
    @NotEmpty
    private String login;

    @JsonProperty("password")
    @Schema(description = "senha do usuario", example = "carlos123")
    @NotEmpty
    private String password;
}

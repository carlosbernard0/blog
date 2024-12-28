package com.carlos.blog_api.dto;

import com.carlos.blog_api.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("idUser")
    @Schema(description = "codigo indentificador do usuario", example = "1")
    private Integer idUser;

    @JsonProperty("username")
    @Schema(description = "username do usuario", example = "carlos007")
    @NotEmpty
    private String username;

    @JsonProperty("emailUser")
    @Schema(description = "email do usuario", example = "1")
    @NotEmpty
    private String emailUser;

    @JsonProperty("passwordUser")
    @Schema(description = "senha do usuario", example = "1")
    @NotEmpty
    private String passwordUser;

    @JsonProperty("role_user")
    @Schema(description = "cargo do usuario", example = "1")
    private UserRole roleUser;

    @JsonProperty("two_factor_enabled")
    @Schema(description = "Auntenticação de dois fatores do usuario habilitada", example = "false")
    private Boolean twoFactorEnabled;
}

package com.github.diogocerqueiralima.authorizationserver.dto;

import com.github.diogocerqueiralima.authorizationserver.validators.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDto(
        @NotBlank String firstName, @NotBlank String lastName, @NotBlank String username, @Email String email,
        @Password String password, @Password String confirmPassword, @NotNull boolean agreement
) {

    public UserRegisterDto() {
        this("", "", "", "", "", "", false);
    }

}

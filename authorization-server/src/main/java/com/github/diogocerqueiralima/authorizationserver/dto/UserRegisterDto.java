package com.github.diogocerqueiralima.authorizationserver.dto;

import com.github.diogocerqueiralima.authorizationserver.validators.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDto(
        @NotBlank(message = "first name should not be blank") String firstName,
        @NotBlank(message = "last name should not be blank") String lastName,
        @NotBlank(message = "username should not be blank") String username,
        @Email(message = "you should insert a valid email") String email,
        @Password(message = "you should insert a valid password") String password,
        @Password(message = "you should insert a valid password") String confirmPassword,
        @NotNull(message = "you should agree with the terms") boolean agreement
) {

    public UserRegisterDto() {
        this("", "", "", "", "", "", false);
    }

}

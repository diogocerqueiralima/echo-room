package com.github.diogocerqueiralima.authorizationserver.dto;

public record UserRegisterDto(
        String firstName, String lastName, String username, String email,
        String password, String confirmPassword, boolean agreement
) {

    public UserRegisterDto() {
        this("", "", "", "", "", "", false);
    }

}

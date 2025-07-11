package com.github.diogocerqueiralima.authorizationserver.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException() {
        this("There is already an account with that email address or username.");
    }

}

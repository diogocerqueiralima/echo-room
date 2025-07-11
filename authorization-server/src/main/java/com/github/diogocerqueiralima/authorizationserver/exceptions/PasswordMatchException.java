package com.github.diogocerqueiralima.authorizationserver.exceptions;

public class PasswordMatchException extends RuntimeException {

    public PasswordMatchException(String message) {
        super(message);
    }

    public PasswordMatchException() {
        this("The passwords do not match");
    }

}

package com.github.diogocerqueiralima.usersservice.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("User with name " + username + " not found");
    }

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }

}

package com.github.diogocerqueiralima.authorizationserver.exceptions;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(UUID uuid) {
        this("Client with id " + uuid.toString() + " not found");
    }

    public ClientNotFoundException() {
        this("Client not found");
    }

}

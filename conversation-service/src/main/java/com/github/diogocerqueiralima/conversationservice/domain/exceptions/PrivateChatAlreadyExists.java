package com.github.diogocerqueiralima.conversationservice.domain.exceptions;

public class PrivateChatAlreadyExists extends RuntimeException {

    public PrivateChatAlreadyExists(String message) {
        super(message);
    }

    public PrivateChatAlreadyExists() {
        this("Private chat already exists with this participants");
    }

}

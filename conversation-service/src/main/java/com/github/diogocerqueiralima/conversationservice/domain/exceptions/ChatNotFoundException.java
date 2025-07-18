package com.github.diogocerqueiralima.conversationservice.domain.exceptions;

public class ChatNotFoundException extends RuntimeException {

    public ChatNotFoundException(Long chatId) {
        super("Chat with id " + chatId + " not found");
    }

}

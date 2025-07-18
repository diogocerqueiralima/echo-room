package com.github.diogocerqueiralima.conversationservice.domain.exceptions;

public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(String message) {
        super(message);
    }

    public ParticipantNotFoundException() {
        this("Participant not found");
    }

}

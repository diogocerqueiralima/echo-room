package com.github.diogocerqueiralima.conversationservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "private_chat")
public class PrivateChat extends Chat {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> blockedBy;

    public PrivateChat() {}

    public PrivateChat(List<Long> participants) {
        super(validateParticipants(participants));
    }

    private static List<Long> validateParticipants(List<Long> participants) {

        if (participants.size() != 2)
            throw new IllegalArgumentException("Participants must have exactly 2 participants");

        return participants;
    }

}

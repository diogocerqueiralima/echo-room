package com.github.diogocerqueiralima.conversationservice.domain.model;

import java.util.List;

public class PrivateChat extends Chat {

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

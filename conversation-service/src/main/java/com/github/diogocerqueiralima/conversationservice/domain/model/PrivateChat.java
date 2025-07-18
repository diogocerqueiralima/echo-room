package com.github.diogocerqueiralima.conversationservice.domain.model;

import com.github.diogocerqueiralima.conversationservice.domain.exceptions.InvalidParticipantsException;

import java.time.Instant;
import java.util.List;

public class PrivateChat extends Chat {

    public PrivateChat(Long id, Instant createdAt, List<Participant> participants) {
        super(id, createdAt, participants);
    }

    public PrivateChat(List<Participant> participants) {
        super(validateParticipants(participants));
    }

    public PrivateChat withParticipants(List<Participant> participants) {
        return new PrivateChat(this.getId(), this.getCreatedAt(), participants);
    }

    private static List<Participant> validateParticipants(List<Participant> participants) {

        if (participants == null || participants.size() != 2 || participants.get(0).equals(participants.get(1)))
            throw new InvalidParticipantsException("Private chat should have exactly two participants");

        return participants;
    }

}

package com.github.diogocerqueiralima.conversationservice.domain.model;

import com.github.diogocerqueiralima.conversationservice.domain.exceptions.InvalidParticipantsException;

import java.time.Instant;
import java.util.List;

public class GroupChat extends Chat {

    private final String name;

    private final String description;

    private final Participant createdBy;

    private final Participant owner;

    private final String image;

    public GroupChat(
            Long id, Instant createdAt, List<Participant> participants, String name, String description,
            Participant createdBy, Participant owner, String image
    ) {
        super(id, createdAt, participants);
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.owner = owner;
        this.image = image;
    }

    public GroupChat(
            List<Participant> participants, String name, String description, Participant createdBy,
            Participant owner, String image
    ) {
        super(validateParticipants(participants));
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.owner = owner;
        this.image = image;
    }

    private static List<Participant> validateParticipants(List<Participant> participants) {

        if (participants == null || participants.size() < 2)
            throw new InvalidParticipantsException("Group chat must have at least two participants");

        long distinctCount = participants.stream()
                .distinct()
                .count();

        if (distinctCount != participants.size())
            throw new InvalidParticipantsException("Participants must be unique");

        return participants;
    }

}

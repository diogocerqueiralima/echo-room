package com.github.diogocerqueiralima.conversationservice.domain.model;

import com.github.diogocerqueiralima.conversationservice.domain.exceptions.InvalidParticipantsException;

import java.util.List;

public class GroupChat extends Chat {

    private String name;

    private String description;

    private Long createdBy;

    private Long owner;

    private String image;

    public GroupChat(List<Participant> participants) {
        super(validateParticipants(participants));
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

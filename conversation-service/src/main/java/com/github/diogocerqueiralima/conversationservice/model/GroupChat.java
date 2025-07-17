package com.github.diogocerqueiralima.conversationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "group_chat")
public class GroupChat extends Chat {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long createdBy;

    @Column(nullable = false)
    private Long owner;

    @Column(nullable = false)
    private String image;

    public GroupChat() {}

    public GroupChat(List<Long> participants) {
        super(validateParticipants(participants));
    }

    private static List<Long> validateParticipants(List<Long> participants) {

        if (participants.size() < 2)
            throw new IllegalArgumentException("Participants must have at least 2 participants");

        return participants;
    }

}

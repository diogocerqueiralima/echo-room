package com.github.diogocerqueiralima.conversationservice.domain.model;

import java.time.Instant;
import java.util.List;

public class Chat {

    private final Long id;

    private final Instant createdAt;

    private List<Participant> participants;

    public Chat(Long id, Instant createdAt, List<Participant> participants) {
        this.id = id;
        this.createdAt = createdAt;
        this.participants = participants;
    }

    public Chat(List<Participant> participants) {
        this(null, null, participants);
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}

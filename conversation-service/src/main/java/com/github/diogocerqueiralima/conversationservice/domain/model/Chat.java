package com.github.diogocerqueiralima.conversationservice.domain.model;

import java.time.Instant;
import java.util.List;

public class Chat {

    private Long id;

    private Instant createdAt;

    private List<Participant> participants;

    public Chat() {}

    public Chat(List<Participant> participants) {
        this.participants = participants;
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

}

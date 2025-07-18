package com.github.diogocerqueiralima.conversationservice.infrastructure.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "chat")
public class ChatEntity {

    @Id
    private final Long id;

    @CreatedDate
    private final Instant createdAt;

    public ChatEntity(Long id, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}

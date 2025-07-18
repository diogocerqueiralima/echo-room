package com.github.diogocerqueiralima.conversationservice.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("private_chat")
public class PrivateChatEntity {

    @Id
    private final Long id;

    public PrivateChatEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

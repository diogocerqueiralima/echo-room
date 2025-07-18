package com.github.diogocerqueiralima.conversationservice.infrastructure.entities;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "chat_participants")
public class ChatParticipantEntity {

    @Column("chat_id")
    private final Long chatId;

    @Column("participant_id")
    private final Long participantId;

    public ChatParticipantEntity(Long chatId, Long participantId) {
        this.chatId = chatId;
        this.participantId = participantId;
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getParticipantId() {
        return participantId;
    }

}

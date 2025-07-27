package com.github.diogocerqueiralima.conversationservice.domain.model;

public record PrivateChatSettings(PrivateChat chat, Participant participant, boolean silenced) {}

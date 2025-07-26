package com.github.diogocerqueiralima.conversationservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record PrivateChatDto(Long id, @JsonProperty("created_at") Instant createdAt, List<Long> participants) {}

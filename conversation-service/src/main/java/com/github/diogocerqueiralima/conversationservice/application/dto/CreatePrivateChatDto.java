package com.github.diogocerqueiralima.conversationservice.application.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record CreatePrivateChatDto(@Size(min = 2, max = 2) List<Long> participants) {}

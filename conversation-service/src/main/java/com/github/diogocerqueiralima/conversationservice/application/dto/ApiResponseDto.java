package com.github.diogocerqueiralima.conversationservice.application.dto;

public record ApiResponseDto<T>(String message, T data) {}

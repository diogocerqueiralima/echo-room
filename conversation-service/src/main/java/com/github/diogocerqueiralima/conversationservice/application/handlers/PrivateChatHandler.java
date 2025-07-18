package com.github.diogocerqueiralima.conversationservice.application.handlers;

import com.github.diogocerqueiralima.conversationservice.application.dto.ApiResponseDto;
import com.github.diogocerqueiralima.conversationservice.application.dto.CreatePrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.application.dto.ParticipantDto;
import com.github.diogocerqueiralima.conversationservice.application.dto.PrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.ChatNotFoundException;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.InvalidParticipantsException;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.ParticipantNotFoundException;
import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;
import com.github.diogocerqueiralima.conversationservice.domain.ports.inbound.PrivateChatService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PrivateChatHandler {

    private final PrivateChatService privateChatService;

    public PrivateChatHandler(PrivateChatService privateChatService) {
        this.privateChatService = privateChatService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreatePrivateChatDto.class)
                .flatMap(privateChatService::create)
                .flatMap(privateChat -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(new ApiResponseDto<>(
                                "Private Chat created successfully",
                                new PrivateChatDto(
                                        privateChat.getId(),
                                        privateChat.getCreatedAt(),
                                        privateChat.getParticipants().stream()
                                                .map(participant -> new ParticipantDto(
                                                        participant.getId(), participant.getName()
                                                ))
                                                .toList()
                                )
                        ))
                )
                .onErrorResume(e -> {

                    if (e instanceof ParticipantNotFoundException)
                        return ServerResponse.status(HttpStatus.NOT_FOUND)
                                .bodyValue(new ApiResponseDto<>(e.getMessage(), null));

                    if (e instanceof InvalidParticipantsException)
                        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .bodyValue(new ApiResponseDto<>(e.getMessage(), null));

                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue(new ApiResponseDto<>(e.getMessage(), null));
                });
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {

        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return privateChatService.getById(id)
                .flatMap(privateChat -> ServerResponse.ok()
                        .bodyValue(new ApiResponseDto<>(
                                "Private Chat created successfully",
                                new PrivateChatDto(
                                        privateChat.getId(),
                                        privateChat.getCreatedAt(),
                                        privateChat.getParticipants().stream()
                                                .map(participant -> new ParticipantDto(
                                                        participant.getId(), participant.getName()
                                                ))
                                                .toList()
                                )
                        ))
                )
                .onErrorResume(e -> {

                    if (e instanceof ChatNotFoundException)
                        return ServerResponse.status(HttpStatus.NOT_FOUND)
                                .bodyValue(new ApiResponseDto<>(e.getMessage(), null));

                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue(new ApiResponseDto<>(e.getMessage(), null));
                });
    }

}

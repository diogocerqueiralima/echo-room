package com.github.diogocerqueiralima.conversationservice.application.services;

import com.github.diogocerqueiralima.conversationservice.application.dto.CreatePrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.ChatNotFoundException;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.PrivateChatAlreadyExists;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.domain.ports.inbound.PrivateChatService;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.PrivateChatRepository;
import com.github.diogocerqueiralima.conversationservice.infrastructure.services.ParticipantServiceImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PrivateChatServiceImpl implements PrivateChatService {

    private final PrivateChatRepository privateChatRepository;
    private final ParticipantServiceImpl participantService;

    public PrivateChatServiceImpl(PrivateChatRepository privateChatRepository, ParticipantServiceImpl participantService) {
        this.privateChatRepository = privateChatRepository;
        this.participantService = participantService;
    }

    @Override
    public Mono<PrivateChat> create(CreatePrivateChatDto dto) {
        return Flux.fromIterable(dto.participants())
                .flatMap(participantService::getById)
                .collectList()
                .flatMap(participants ->
                        privateChatRepository.existsByParticipants(participants)
                                .flatMap(exists ->
                                        exists ? Mono.error(new PrivateChatAlreadyExists()) :
                                                privateChatRepository.save(new PrivateChat(participants))
                                )
                );
    }

    @Override
    public Mono<PrivateChat> getById(Long id) {
        return privateChatRepository.findById(id)
                .switchIfEmpty(Mono.error(new ChatNotFoundException(id)))
                .flatMap(privateChat -> Flux.fromIterable(privateChat.getParticipants())
                        .flatMap(participant -> participantService.getById(participant.getId()))
                        .collectList()
                        .map(privateChat::withParticipants)
                );
    }
}

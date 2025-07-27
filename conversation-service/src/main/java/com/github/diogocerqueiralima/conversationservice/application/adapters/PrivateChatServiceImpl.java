package com.github.diogocerqueiralima.conversationservice.application.adapters;

import com.github.diogocerqueiralima.conversationservice.application.dto.CreatePrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.ChatNotFoundException;
import com.github.diogocerqueiralima.conversationservice.domain.exceptions.PrivateChatAlreadyExists;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.domain.ports.inbound.PrivateChatService;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.ParticipantGateway;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.PrivateChatRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PrivateChatServiceImpl implements PrivateChatService {

    private final PrivateChatRepository privateChatRepository;
    private final ParticipantGateway participantGateway;

    public PrivateChatServiceImpl(PrivateChatRepository privateChatRepository, ParticipantGateway participantGateway) {
        this.privateChatRepository = privateChatRepository;
        this.participantGateway = participantGateway;
    }

    @Override
    public Mono<PrivateChat> create(CreatePrivateChatDto dto) {
        return Flux.fromIterable(dto.participants())
                .flatMap(participantGateway::getById)
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
                .switchIfEmpty(Mono.error(new ChatNotFoundException(id)));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return privateChatRepository.deleteById(id);
    }

}

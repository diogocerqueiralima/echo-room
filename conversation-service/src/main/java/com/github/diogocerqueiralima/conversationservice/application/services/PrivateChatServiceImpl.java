package com.github.diogocerqueiralima.conversationservice.application.services;

import com.github.diogocerqueiralima.conversationservice.application.dto.CreatePrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.domain.ports.inbound.PrivateChatService;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.PrivateChatRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PrivateChatServiceImpl implements PrivateChatService {

    private final PrivateChatRepository privateChatRepository;

    public PrivateChatServiceImpl(PrivateChatRepository privateChatRepository) {
        this.privateChatRepository = privateChatRepository;
    }

    @Override
    public Mono<PrivateChat> create(CreatePrivateChatDto dto) {
        return null;
    }

}

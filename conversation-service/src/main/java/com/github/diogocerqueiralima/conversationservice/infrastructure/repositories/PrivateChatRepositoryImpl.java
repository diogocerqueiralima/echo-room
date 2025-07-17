package com.github.diogocerqueiralima.conversationservice.infrastructure.repositories;

import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.PrivateChatRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PrivateChatRepositoryImpl implements PrivateChatRepository {

    @Override
    public Mono<PrivateChat> save(PrivateChat privateChat) {
        return null;
    }

}

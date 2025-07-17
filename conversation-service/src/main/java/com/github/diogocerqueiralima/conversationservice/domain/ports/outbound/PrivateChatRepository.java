package com.github.diogocerqueiralima.conversationservice.domain.ports.outbound;

import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import reactor.core.publisher.Mono;

public interface PrivateChatRepository {

    Mono<PrivateChat> save(PrivateChat privateChat);

}

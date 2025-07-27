package com.github.diogocerqueiralima.conversationservice.domain.ports.inbound;

import com.github.diogocerqueiralima.conversationservice.application.dto.CreatePrivateChatDto;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import reactor.core.publisher.Mono;

public interface PrivateChatService {

    Mono<PrivateChat> create(CreatePrivateChatDto dto);

    Mono<PrivateChat> getById(Long id);

    Mono<Void> deleteById(Long id);

}

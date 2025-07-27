package com.github.diogocerqueiralima.conversationservice.domain.ports.outbound;

import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PrivateChatRepository {

    Mono<PrivateChat> save(PrivateChat privateChat);

    Mono<PrivateChat> findById(Long id);

    Mono<Boolean> existsByParticipants(List<Participant> participants);

    Mono<Void> deleteById(Long id);

}

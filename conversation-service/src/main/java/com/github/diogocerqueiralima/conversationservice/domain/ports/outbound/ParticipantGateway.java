package com.github.diogocerqueiralima.conversationservice.domain.ports.outbound;

import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;
import reactor.core.publisher.Mono;

public interface ParticipantGateway {

    Mono<Participant> getById(Long id);

}

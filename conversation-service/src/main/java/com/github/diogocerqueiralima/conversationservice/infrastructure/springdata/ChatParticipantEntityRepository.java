package com.github.diogocerqueiralima.conversationservice.infrastructure.springdata;

import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.ChatParticipantEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ChatParticipantEntityRepository extends ReactiveCrudRepository<ChatParticipantEntity, Long> {

    Flux<ChatParticipantEntity> findByChatId(Long chatId);

}

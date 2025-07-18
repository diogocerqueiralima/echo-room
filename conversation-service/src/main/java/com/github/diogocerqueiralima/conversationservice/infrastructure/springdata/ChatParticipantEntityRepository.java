package com.github.diogocerqueiralima.conversationservice.infrastructure.springdata;

import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.ChatParticipantEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatParticipantEntityRepository extends ReactiveCrudRepository<ChatParticipantEntity, Long> {

    Flux<ChatParticipantEntity> findByChatId(Long chatId);

    @Query("""
        SELECT EXISTS (
            SELECT chat_id
            FROM chat_participants
            WHERE participant_id IN (:participantIds)
            GROUP BY chat_id
            HAVING COUNT(DISTINCT participant_id) = :count
        )
    """)
    Mono<Boolean> existsChatByParticipantIds(List<Long> participantIds, int count);

}

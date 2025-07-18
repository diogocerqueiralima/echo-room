package com.github.diogocerqueiralima.conversationservice.infrastructure.springdata;

import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.PrivateChatEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PrivateChatEntityRepository extends ReactiveCrudRepository<PrivateChatEntity, Long> {

    @Query("INSERT INTO private_chat (id) VALUES (:id)")
    Mono<Void> insertById(Long id);

}

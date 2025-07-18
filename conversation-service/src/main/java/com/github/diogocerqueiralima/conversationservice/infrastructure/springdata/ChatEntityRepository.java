package com.github.diogocerqueiralima.conversationservice.infrastructure.springdata;

import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.ChatEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatEntityRepository extends ReactiveCrudRepository<ChatEntity, Long> {}

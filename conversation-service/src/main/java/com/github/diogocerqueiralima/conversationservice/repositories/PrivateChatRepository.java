package com.github.diogocerqueiralima.conversationservice.repositories;

import com.github.diogocerqueiralima.conversationservice.model.PrivateChat;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChatRepository extends ReactiveCrudRepository<PrivateChat, Long> {}

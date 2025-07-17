package com.github.diogocerqueiralima.conversationservice.repositories;

import com.github.diogocerqueiralima.conversationservice.model.GroupChat;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends ReactiveCrudRepository<GroupChat, Long> {}

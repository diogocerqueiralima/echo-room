package com.github.diogocerqueiralima.conversationservice.services;

import com.github.diogocerqueiralima.conversationservice.exceptions.InvalidParticipantsException;
import com.github.diogocerqueiralima.conversationservice.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.repositories.PrivateChatRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PrivateChatService {

    private final PrivateChatRepository privateChatRepository;
    private final UserService userService;

    public PrivateChatService(PrivateChatRepository privateChatRepository, UserService userService) {
        this.privateChatRepository = privateChatRepository;
        this.userService = userService;
    }

    public Mono<PrivateChat> create(List<Long> participants) {

        if (participants.size() != 2)
            return Mono.error(new InvalidParticipantsException("Private chats must have two participants"));

        Long id1 = participants.get(0);
        Long id2 = participants.get(1);

        return Mono.zip(userService.existsById(id1), userService.existsById(id2), (b1, b2) -> {

            if (!b1 || !b2)
                throw new InvalidParticipantsException("Private chats must have two participants");

            return participants;
        }).flatMap(ids -> privateChatRepository.save(new PrivateChat(ids)));
    }

}

package com.github.diogocerqueiralima.conversationservice.infrastructure.adapters;

import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;
import com.github.diogocerqueiralima.conversationservice.domain.model.PrivateChat;
import com.github.diogocerqueiralima.conversationservice.domain.ports.outbound.PrivateChatRepository;
import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.ChatEntity;
import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.ChatParticipantEntity;
import com.github.diogocerqueiralima.conversationservice.infrastructure.entities.PrivateChatEntity;
import com.github.diogocerqueiralima.conversationservice.infrastructure.springdata.ChatEntityRepository;
import com.github.diogocerqueiralima.conversationservice.infrastructure.springdata.ChatParticipantEntityRepository;
import com.github.diogocerqueiralima.conversationservice.infrastructure.springdata.PrivateChatEntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class PrivateChatRepositoryImpl implements PrivateChatRepository {

    private final ChatEntityRepository chatRepository;
    private final PrivateChatEntityRepository privateChatRepository;
    private final ChatParticipantEntityRepository participantRepository;
    private final TransactionalOperator transactionalOperator;

    public PrivateChatRepositoryImpl(
            ChatEntityRepository chatRepository, PrivateChatEntityRepository privateChatRepository,
            ChatParticipantEntityRepository participantRepository, TransactionalOperator transactionalOperator
    ) {
        this.chatRepository = chatRepository;
        this.privateChatRepository = privateChatRepository;
        this.participantRepository = participantRepository;
        this.transactionalOperator = transactionalOperator;
    }

    public Mono<PrivateChat> save(PrivateChat privateChat) {

        ChatEntity chatEntity = new ChatEntity(privateChat.getId(), privateChat.getCreatedAt());

        return transactionalOperator.transactional(
                chatRepository.save(chatEntity)
                        .flatMap(savedChat -> {

                            PrivateChatEntity privateChatEntity = new PrivateChatEntity(savedChat.getId());
                            List<ChatParticipantEntity> participants = privateChat.getParticipants().stream()
                                    .map(p -> new ChatParticipantEntity(savedChat.getId(), p.getId()))
                                    .toList();

                            Mono<Void> savePrivateChat = privateChatRepository.insertById(privateChatEntity.getId());

                            Mono<Void> saveParticipants = Flux.fromIterable(participants)
                                    .flatMap(participantRepository::save)
                                    .then();

                            return savePrivateChat
                                    .then(saveParticipants)
                                    .then(Mono.just(
                                            new PrivateChat(
                                                    savedChat.getId(),
                                                    savedChat.getCreatedAt(),
                                                    privateChat.getParticipants()
                                            )
                                    ));
                        })
        );
    }

    @Override
    public Mono<PrivateChat> findById(Long id) {
        return chatRepository.findById(id)
                .flatMap(chatEntity ->
                        privateChatRepository.existsById(chatEntity.getId())
                                .flatMap(isPrivateChat -> {

                                    if (!isPrivateChat)
                                        return Mono.empty();

                                    return participantRepository.findByChatId(chatEntity.getId())
                                            .collectList()
                                            .map(participantEntities -> {

                                                List<Participant> participants = participantEntities.stream()
                                                        .map(p -> new Participant(p.getParticipantId()))
                                                        .toList();

                                                return new PrivateChat(
                                                        chatEntity.getId(),
                                                        chatEntity.getCreatedAt(),
                                                        participants
                                                );
                                            });
                                })
                );
    }

    @Override
    public Mono<Boolean> existsByParticipants(List<Participant> participants) {
        return participantRepository.existsChatByParticipantIds(
                participants.stream()
                        .map(Participant::getId)
                        .toList(),
                participants.size()
        );
    }

}

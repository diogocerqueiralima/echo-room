package com.github.diogocerqueiralima.conversationservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "chat")
@Inheritance(strategy = InheritanceType.JOINED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> participants;

    public Chat() {}

    public Chat(List<Long> participants) {
        this.participants = participants;
    }

}

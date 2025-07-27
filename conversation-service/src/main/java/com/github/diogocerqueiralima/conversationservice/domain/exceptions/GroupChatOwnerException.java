package com.github.diogocerqueiralima.conversationservice.domain.exceptions;

import com.github.diogocerqueiralima.conversationservice.domain.model.Participant;

public class GroupChatOwnerException extends RuntimeException {

    public GroupChatOwnerException(String message) {
        super(message);
    }

    public GroupChatOwnerException(Participant participant) {
        super("Participant " + participant.id() + " is not the owner of this group");
    }

}

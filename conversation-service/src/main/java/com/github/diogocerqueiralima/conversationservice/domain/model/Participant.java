package com.github.diogocerqueiralima.conversationservice.domain.model;

import java.util.Objects;

public class Participant {

    private final Long id;
    private String name;

    public Participant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Participant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

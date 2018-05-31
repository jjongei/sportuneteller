package com.sportuenteller.olympic.games.vote.domain.voter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class VoterId implements Serializable {

    @Column(name = "session_key", columnDefinition = "nvarchar(128)", nullable = false)
    private String value;

    protected VoterId(){}

    public VoterId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoterId voterId = (VoterId) o;

        return value != null ? value.equals(voterId.value) : voterId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

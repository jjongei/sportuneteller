package com.sportuenteller.olympic.games.vote.domain.team;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class TeamId implements Serializable {

    @Column(name = "team_id", columnDefinition = "bigint", nullable = false)
    private Long value;

    protected TeamId(){}

    public TeamId(Long value){
        this.value = value;
    }

    public Long getValue(){return this.value;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamId teamId = (TeamId) o;

        return value != null ? value.equals(teamId.value) : teamId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

package com.sportuenteller.olympic.manage.plyers.domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class PlayerId implements Serializable {

    @Column(name = "player_id", columnDefinition = "bigint", nullable = false)
    private Long value;

    protected PlayerId(){}

    public PlayerId(Long value){
        this.value = value;
    }

    public Long getValue(){return this.value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerId playerId = (PlayerId) o;

        return value != null ? value.equals(playerId.value) : playerId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

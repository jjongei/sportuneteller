package com.sportuenteller.olympic.games.game.domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class GameId implements Serializable {

    @Column(name = "game_id", columnDefinition = "bigint", nullable = false)
    private Long value;

    protected GameId(){}

    public GameId(Long value){
        this.value = value;
    }

    public Long getValue(){return this.value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameId gameId = (GameId) o;

        return value != null ? value.equals(gameId.value) : gameId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

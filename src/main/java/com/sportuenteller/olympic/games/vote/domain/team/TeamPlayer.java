package com.sportuenteller.olympic.games.vote.domain.team;

import com.sportuenteller.olympic.common.model.Players;
import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class TeamPlayer {

    @Embedded
    private Players player;

    protected TeamPlayer(){}

    public TeamPlayer(Players player) {
       this.setPlayer(player);
    }

    private void setPlayer(Players player) {
        if(player == null) throw new IllegalArgumentException("Player");
        this.player = player;
    }
}

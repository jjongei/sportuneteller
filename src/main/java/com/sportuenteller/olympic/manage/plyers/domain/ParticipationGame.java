package com.sportuenteller.olympic.manage.plyers.domain;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class ParticipationGame {

    @Column(name = "game_id", columnDefinition = "bigint", nullable = false)
    private Long gameId;

    @Column(name = "game_name_kr", columnDefinition = "nvarchar(128)", nullable = false)
    private String gameNameKr;
    @Column(name = "game_name_en", columnDefinition = "nvarchar(128)", nullable = false)
    private String gameNameEn;

    protected ParticipationGame(){}

    public ParticipationGame(Long gameId, String gameNameKr, String gameNameEn) {
        this.setGameId(gameId);
        this.setGameNameKr(gameNameKr);
        this.setGameNameEn(gameNameEn);
    }

    public void setGameId(Long gameId) {
        if(gameId == null) throw new IllegalArgumentException("GameId");
        this.gameId = gameId;
    }

    public void setGameNameKr(String gameNameKr) {
        if(gameNameKr == null) throw new IllegalArgumentException("GameNameKR");
        this.gameNameKr = gameNameKr;
    }

    public void setGameNameEn(String gameNameEn) {
        if(gameNameEn == null) throw new IllegalArgumentException("GameNameEn");
        this.gameNameEn = gameNameEn;
    }
}

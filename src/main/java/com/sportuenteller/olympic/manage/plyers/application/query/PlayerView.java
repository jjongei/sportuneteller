package com.sportuenteller.olympic.manage.plyers.application.query;

import com.sportuenteller.olympic.manage.plyers.domain.Player;
import lombok.Getter;

@Getter
public class PlayerView {

    private long playerId;
    private String gameTypeName;
    private String gameTypeCode;
    private String gameTypeSubject;
    private String countryCode;
    private String name;
    private boolean sexuality;

    public PlayerView(Player player){
        this.playerId = player.getId().getValue();
        this.gameTypeName = player.getGameType().name();
        this.gameTypeCode = player.getGameType().getCode();
        this.gameTypeSubject = player.getGameType().getSubject();
        this.countryCode = player.getCountryId().getValue();
        this.name = player.getName();
        this.sexuality = player.getSexuality() != null ? player.getSexuality() : false;
    }
}

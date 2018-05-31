package com.sportuenteller.olympic.games.vote.application.rank;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.code.MedalType;
import com.sportuenteller.olympic.common.code.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MyStatusGame {

    private GameType gameTypeCode;
    private Long gameId;
    private String gameGameKr;
    private String gameGameEn;
    private boolean group;
    private Long teamId;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private MedalType medalCode;
    private StatusType statusType;
    private Set<String> players ;

    public MyStatusGame(GameType gameTypeCode
            , Long gameId
            , String gameGameKr
            , String gameGameEn
            , boolean group
            , Long teamId
            , String countryCode
            , String countryNameKr
            , String countryNameEn
            , MedalType medalCode
            , StatusType statusType
            , Set<String> players) {
        this.gameTypeCode = gameTypeCode;
        this.gameId = gameId;
        this.gameGameKr = gameGameKr;
        this.gameGameEn = gameGameEn;
        this.group = group;
        this.teamId = teamId;
        this.countryCode = countryCode;
        this.countryNameKr = countryNameKr;
        this.countryNameEn = countryNameEn;
        this.medalCode = medalCode;
        this.statusType = statusType;
        this.players = players;
    }
}

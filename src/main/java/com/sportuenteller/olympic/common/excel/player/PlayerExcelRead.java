package com.sportuenteller.olympic.common.excel.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerExcelRead {
    private PlayerInfo playerInfo;
    private GameInfo gameInfo;
    private List<GameInfo> games;
    public PlayerExcelRead(String id
            , String name
            , String sexuality
            , String countryCode
            , String countryReference
            , String code
            , String gameId
            , String gameNameKr
            , String gameNameEn
            , String modify) {

        this.playerInfo = new PlayerInfo(id != null ? Long.parseLong(id) : 0L
                , code
                , name
                , sexuality != null && "T".equals(sexuality)
                , countryCode
                , countryReference
                , modify != null && "T".equals(modify));
        this.gameInfo = new GameInfo(gameId != null ? Long.parseLong(gameId) : 0L, gameNameKr, gameNameEn);
    }

    public PlayerExcelRead(PlayerInfo playerInfo, List<GameInfo> games) {
        this.playerInfo = playerInfo;
        this.games = games;
    }
}

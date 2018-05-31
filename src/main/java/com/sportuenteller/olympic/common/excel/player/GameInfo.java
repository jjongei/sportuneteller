package com.sportuenteller.olympic.common.excel.player;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameInfo {
    private long gameId;
    private String gameNameKr;
    private String gameNameEn;

    public GameInfo(long gameId, String gameNameKr, String gameNameEn) {
        this.gameId = gameId;
        this.gameNameKr = gameNameKr;
        this.gameNameEn = gameNameEn;
    }
}

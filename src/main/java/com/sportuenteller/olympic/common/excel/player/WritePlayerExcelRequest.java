package com.sportuenteller.olympic.common.excel.player;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WritePlayerExcelRequest {
    private String playerId;
    private String name;
    private String sexuality;
    private String countryCode;
    private String countryReference;
    private String code;
    private String gameId;
    private String gameNameKr;
    private String gameNameEn;

    public WritePlayerExcelRequest(long playerId
            , String name
            , Boolean sexuality
            , String countryCode
            , String countryReference
            , String code
            , long gameId
            , String gameNameKr
            , String gameNameEn) {
        this.playerId = Long.toString(playerId);
        this.name = name;
        this.sexuality = sexuality != null && sexuality ? "T" : "F";
        this.countryCode = countryCode;
        this.countryReference = countryReference;
        this.code = code;
        this.gameId = Long.toString(gameId);
        this.gameNameKr = gameNameKr;
        this.gameNameEn = gameNameEn;
    }
}

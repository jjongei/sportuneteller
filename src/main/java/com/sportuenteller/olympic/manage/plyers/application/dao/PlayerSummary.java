package com.sportuenteller.olympic.manage.plyers.application.dao;

import com.sportuenteller.olympic.common.code.GameType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerSummary {

    private long playerId;
    private GameType gameType;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private String playerName;
    private boolean sexuality;
    private int rowNumber;
}

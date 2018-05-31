package com.sportuenteller.olympic.games.vote.application.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface PlayerSummaryListClient {
    @Data
    @NoArgsConstructor
    public class PlayerItem {
        private long playerId;
        private String countryCode;
        private String countryNameEn;
        private String countryNameKr;
        private String name;
        private boolean sexuality;

        public PlayerItem(long playerId, String countryCode, String countryNameKr, String countryNameEn, String name, boolean sexuality) {
            this.playerId = playerId;
            this.countryCode = countryCode;
            this.countryNameKr = countryNameKr;
            this.countryNameEn = countryNameEn;
            this.name = name;
            this.sexuality = sexuality;
        }
    }

    public List<PlayerItem> requestPlayerItemLIst(String gameType, String countryCode, boolean male, boolean female, long gameId);

    public List<PlayerItem> requestPlayerItemLIst(List<Long> idList);

    public List<PlayerItem> requestPlayerItemLIst(long gameId);


}

package com.sportuenteller.olympic.rest.games.applicaiton.count;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface VotesPerGameCountListClient {
    @Data
    @NoArgsConstructor
    public class GameCountItem {
        private String gameTypeCode;
        private int count;
        private boolean hotGame;
        private boolean reward;
        private boolean voteAvailable;

        public GameCountItem(String gameTypeCode, int count, boolean hotGame, boolean reward, boolean voteAvailable) {
            this.gameTypeCode = gameTypeCode;
            this.count = count;
            this.hotGame = hotGame;
            this.reward = reward;
            this.voteAvailable = voteAvailable;
        }
    }

    public List<GameCountItem> requestVotePerGameCountList();
}

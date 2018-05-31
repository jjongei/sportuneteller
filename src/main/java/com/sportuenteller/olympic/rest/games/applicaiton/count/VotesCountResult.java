package com.sportuenteller.olympic.rest.games.applicaiton.count;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class VotesCountResult implements Serializable {
    private String gameTypeCode;
    private int count;
    private boolean hotGame;
    private boolean reward;
    private boolean voteAvailable;

    public VotesCountResult(String gameTypeCode, int count, boolean hotGame, boolean reward, boolean voteAvailable) {
        this.gameTypeCode = gameTypeCode;
        this.count = count;
        this.hotGame = hotGame;
        this.reward = reward;
        this.voteAvailable = voteAvailable;
    }
}

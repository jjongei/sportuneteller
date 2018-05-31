package com.sportuenteller.olympic.games.game.application.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotePerGameCount {
    private String gameTypeCode;
    private int count;
    private boolean hotGame;
    private boolean reward;
    private boolean voteAvailable;

    public VotePerGameCount(Object gameTypeCode, Object hotGame, Object reward, Object voteCount, Object count){
        this.gameTypeCode = gameTypeCode != null ? (String) gameTypeCode : null;
        this.count = count != null ? (Integer) count : 0;
        this.hotGame = hotGame != null ? "T".equals((String) hotGame) : false;
        this.reward = reward != null ? "T".equals((String) reward) : false;
        this.voteAvailable = voteCount != null && (Integer)voteCount !=0 ? true : false;
    }
}

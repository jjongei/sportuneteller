package com.sportuenteller.olympic.games.vote.application.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamPlayerView {

    private Long playerId;
    private String playerName;

    public TeamPlayerView(Long playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }
}

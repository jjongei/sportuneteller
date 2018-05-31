package com.sportuenteller.olympic.games.vote.application.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VoteTeamView {

    private Long gameId;
    private Long teamId;
    private Date voteDate;

    public VoteTeamView(Long gameId, Long teamId, Date voteDate) {
        this.gameId = gameId;
        this.teamId = teamId;
        this.voteDate = voteDate;
    }

}

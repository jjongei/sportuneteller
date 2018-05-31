package com.sportuenteller.olympic.games.vote.domain.voter;


import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.vote.domain.team.TeamId;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class VoteTeam {

    @Embedded
    private GameId gameId;

    @Embedded
    private TeamId teamId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vote_date", columnDefinition = "datetime", nullable = false)
    private Date voteDate;

    protected VoteTeam(){}

    public VoteTeam(GameId gameId, TeamId teamId, Date voteDate) {
       this.setGameId(gameId);
       this.setTeamId(teamId);
       this.setVoteDate(voteDate);
    }

    private void setGameId(GameId gameId) {
        if(gameId == null) throw new IllegalArgumentException("Game ID");
        this.gameId = gameId;
    }

    private void setTeamId(TeamId teamId) {
        if(teamId == null) throw new IllegalArgumentException("Team ID");
        this.teamId = teamId;
    }

    private void setVoteDate(Date voteDate) {
        if(voteDate == null) throw new IllegalArgumentException("Vote date");
        this.voteDate = voteDate;
    }
}

package com.sportuenteller.olympic.rest.games.applicaiton.status;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamVoteStatusResult implements Serializable {
    private String gameCode;
    private Long detailGameSeq;
    private Long teamSeq;
    private List<TeamVoteRank> ranks;
    private List<TeamVoteRank> votes;

    public TeamVoteStatusResult(String gameCode, long detailGameSeq, long teamSeq, List<TeamVoteRank> ranks , List<TeamVoteRank> votes) {
        this.gameCode = gameCode;
        this.detailGameSeq = detailGameSeq;
        this.teamSeq = teamSeq;
        this.ranks = ranks;
        this.votes = votes;
    }
}

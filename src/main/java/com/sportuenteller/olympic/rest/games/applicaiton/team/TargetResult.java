package com.sportuenteller.olympic.rest.games.applicaiton.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class TargetResult implements Serializable {
    private String gameCode;
    private Long detailGameSeq;
    private String detailGameKr;
    private String detailGameEn;
    private String statusCode;
    private String votingSchedule;
    private boolean groupGame;
    private int currentPage;
    private int previousPage;
    private int nextPage;

    private Long myVoteTeamSeq = 0L;

    private List<TeamResult> teams = new ArrayList<>();

    public TargetResult(String gameCode
            , Long detailGameSeq
            , String detailGameKr
            , String detailGameEn
            , String statusCode
            , String votingSchedule
            , boolean groupGame
            , int currentPage
            , int previousPage
            , int nextPage) {
        this.gameCode = gameCode;
        this.detailGameSeq = detailGameSeq;
        this.detailGameKr = detailGameKr;
        this.detailGameEn = detailGameEn;
        this.statusCode = statusCode;
        this.votingSchedule = votingSchedule;
        this.groupGame = groupGame;
        this.currentPage = currentPage;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
    }
    public void setTeams(List<TeamResult> teams){
        this.teams = teams;

        this.myVoteTeamSeq = this.teams == null ? 0L : this.teams
                .stream()
                .filter(f -> f.isMyVote())
                .map(f->f.getTeamSeq()).findFirst().orElse(0L);


    }
}

package com.sportuenteller.olympic.rest.rank.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MyStatusGameResult implements Serializable {
    private String gameCode;
    private Long detailGameSeq;
    private String detailGameKr;
    private String detailGameEn;
    private boolean groupGame;
    private boolean voteAvaliable;
    private MyStatusTeamResult team;

    public MyStatusGameResult(String gameCode, Long detailGameSeq, String detailGameKr, String detailGameEn, boolean groupGame, boolean voteAvaliable, MyStatusTeamResult team) {
        this.gameCode = gameCode;
        this.detailGameSeq = detailGameSeq;
        this.detailGameKr = detailGameKr;
        this.detailGameEn = detailGameEn;
        this.groupGame = groupGame;
        this.voteAvaliable = voteAvaliable;
        this.team = team;
    }
}

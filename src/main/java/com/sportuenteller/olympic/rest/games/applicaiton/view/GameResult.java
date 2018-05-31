package com.sportuenteller.olympic.rest.games.applicaiton.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GameResult implements Serializable {
    private String gameCode;
    private Long detailGameSeq;
    private String detailGameKr;
    private String detailGameEn;
    private String statusCode;
    private String votingSchedule;

    public GameResult(String gameCode, Long detailGameSeq, String detailGameKr, String detailGameEn, String statusCode, String votingSchedule) {
        this.gameCode = gameCode;
        this.detailGameSeq = detailGameSeq;
        this.detailGameKr = detailGameKr;
        this.detailGameEn = detailGameEn;
        this.statusCode = statusCode;
        this.votingSchedule = votingSchedule;
    }
}

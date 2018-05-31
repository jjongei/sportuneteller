package com.sportuenteller.olympic.rest.games.applicaiton.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamResult implements Serializable {

    private Long teamSeq;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private List<String> players = new ArrayList<>();
    private String medalCode;
    private Long vote;
    private boolean available = true;
    private boolean myVote;

    public TeamResult(Long teamSeq
            , String countryCode
            , String countryNameKr
            , String countryNameEn
            , List<String> players
            , String medalCode
            , Long vote
            , boolean available
            , boolean myVote) {
        this.teamSeq = teamSeq;
        this.countryCode = countryCode;
        this.countryNameKr = countryNameKr;
        this.countryNameEn = countryNameEn;
        this.players = players;
        this.medalCode = medalCode;
        this.vote = vote;
        this.available = available;
        this.myVote = myVote;
    }
}

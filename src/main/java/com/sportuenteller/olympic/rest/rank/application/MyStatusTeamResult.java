package com.sportuenteller.olympic.rest.rank.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MyStatusTeamResult implements Serializable {

    private Long teamSeq;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private String medalCode;
    private List<String> players = new ArrayList<>();

    public MyStatusTeamResult(Long teamSeq, String countryCode, String countryNameKr, String countryNameEn
            , String medalCode, List<String> players) {
        this.teamSeq = teamSeq;
        this.countryCode = countryCode;
        this.countryNameKr = countryNameKr;
        this.countryNameEn = countryNameEn;
        this.players = players;
        this.medalCode = medalCode;
    }
}

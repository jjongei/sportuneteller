package com.sportuenteller.olympic.games.vote.application.dao;


import com.sportuenteller.olympic.common.code.MedalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamSummary {

    private long teamId;
    private long gameId;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private MedalType medalType;
    private long vote;
    private boolean available;

    private Set<String> players;

    private List<VoteTeamSummary> voters;

    public void setVoters(List<VoteTeamSummary> voters){
        if(voters != null){
            this.voters = voters.stream().filter(f -> f.getTeamId() == teamId).collect(Collectors.toList());
        }
    }


}

package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.games.vote.domain.team.Team;
import com.sportuenteller.olympic.games.vote.domain.team.TeamPlayer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TeamView {
    private long teamId;
    private long gameId;
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private String medalTypeName;
    private long vote;
    private boolean available;

    private List<TeamPlayerView> teamPlayers;

    public TeamView(Team team){
        this.teamId = team.getId().getValue();
        this.gameId = team.getGameId().getValue();
        this.countryCode = team.getCountries().getCode();
        this.countryNameKr = team.getCountries().getNameKr();
        this.countryNameEn = team.getCountries().getNameEn();
        this.medalTypeName = team.getMedalType() != null ? team.getMedalType().name() : null;
        this.vote = team.getVote() != null ? team.getVote() : 0;
        this.available = team.getVotingAvailable() !=null ? team.getVotingAvailable() : false;

        this.teamPlayers = new ArrayList<>();

        List<TeamPlayer>  players = team.getTeamPlayers();
        if(players != null){
            this.teamPlayers.addAll(players.stream().map(m -> new TeamPlayerView(m.getPlayer().getId(), m.getPlayer().getName())).collect(Collectors.toList()));
        }
    }

}

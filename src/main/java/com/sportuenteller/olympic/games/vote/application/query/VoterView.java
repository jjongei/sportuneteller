package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.games.vote.domain.voter.VoteTeam;
import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class VoterView {

    private String voterId;
    private String userId;
    private String name;
    private Long goldCount;
    private Long silverCount;
    private Long bronzeCount;
    private Long point;
    private List<VoteTeamView> voteTeams;

    protected VoterView(Voter voter){
        this.voterId = voter.getId().getValue();
        this.userId = voter.getUserId();
        this.name = voter.getName();
        this.goldCount = voter.getGoldCount();
        this.silverCount = voter.getSlverCount();
        this.bronzeCount = voter.getBronzeCount();
        this.point = voter.getPoint();

        List<VoteTeam> teams = voter.getVoteTeams();

        if(teams != null){
            this.voteTeams =teams.stream()
                    .map(m -> new VoteTeamView(m.getGameId().getValue(), m.getTeamId().getValue(), m.getVoteDate())).collect(Collectors.toList());
        }
    }

    public long getVoteCount(){
        return this.voteTeams != null ? this.voteTeams.size() : 0;
    }


}

package com.sportuenteller.olympic.games.vote.application.service;

import com.sportuenteller.olympic.common.code.StatusType;
import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.common.utils.Properties;
import com.sportuenteller.olympic.games.game.application.query.GameView;
import com.sportuenteller.olympic.games.game.application.query.GameViewService;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.vote.domain.team.Team;
import com.sportuenteller.olympic.games.vote.domain.team.TeamId;
import com.sportuenteller.olympic.games.vote.domain.team.TeamRepository;
import com.sportuenteller.olympic.games.vote.domain.voter.VoteTeam;
import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterId;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterRepository;
import com.sportuenteller.olympic.rest.auth.application.query.MemberView;
import com.sportuenteller.olympic.rest.auth.application.query.MemberViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoteService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    GameViewService gameViewService;

    @Autowired
    MemberViewService memberViewService;
    @Autowired
    Properties properties;

    @Transactional()
    public void vote(long gameId, long teamId, String sessionKey){
        GameView gameView = gameViewService.findGameView(gameId);
        if(gameView == null){
            throw  new NotFoundGameException();
        }
        if(!StatusType.possible.name().equals(gameView.getStatusTypeName())){
            throw new NotPossibleToVoteExcepiton("It is not possible to vote");
        }

        Voter voter = voterRepository.findById(new VoterId(sessionKey));
        if(voter == null){
            voter =  this.register(new GameId(gameId), new TeamId(teamId), sessionKey);
            return;
        }
        this.modify(voter, new GameId(gameId), new TeamId(teamId));
    }

    private Voter register(GameId gameId, TeamId teamId, String sessionKey){
        MemberView view = memberViewService.findMemberView(sessionKey);

        List<VoteTeam> voteTeams = new ArrayList<>();
        voteTeams.add(new VoteTeam(gameId, teamId, DateUtil.getCurrentDate()));

        Voter voter = new Voter(new VoterId(sessionKey)
                , view.getUserId()
                , view.getName()
                , voteTeams
                , properties.getPoint());

        this.voterRepository.save(voter);

        Team team = teamRepository.findById(teamId);

        if(team != null){
            team.changeVote(1);
        }

        return voter;
    }

    private void modify(Voter voter, GameId gameId, TeamId teamId){
        //Vote process
        List<TeamId> voteTeams =voter.vote(gameId,  teamId);
        boolean checkTeamIdExist = false;

        if(voteTeams != null){
            Map<Long, List<TeamId>> grouped = voteTeams.stream()
                    .collect(Collectors.groupingBy(x -> x.getValue()));

            checkTeamIdExist = this.changeVoteCount(grouped, teamId);
        }

        if(!checkTeamIdExist){
            Team team = teamRepository.findById(teamId);
            if(team != null){
                team.changeVote(1);
            }
        }
    }
    private  boolean changeVoteCount(Map<Long, List<TeamId>> grouped, TeamId teamId){
       boolean flag = false;
        Iterator<Long> itr = grouped.keySet().iterator();
        while (itr.hasNext()){
            Long key = itr.next();
            List<TeamId> idList = grouped.get(key);
            TeamId id = idList.stream().findFirst().orElse(null);

            if(id != null){
                if(id.equals(teamId)){
                    flag = true;
                }
                Team team = teamRepository.findById(id);
                if(team != null){
                    team.changeVote(-1*idList.size());
                }
            }
        }

        return flag;
    }
}

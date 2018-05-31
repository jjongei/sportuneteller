package com.sportuenteller.olympic.rest.games.applicaiton.status;

import com.sportuenteller.olympic.games.vote.application.dao.TeamVoteStatus;
import com.sportuenteller.olympic.games.vote.application.dao.TeamVoteStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestTeamVoteStatusService {

    @Autowired
    TeamVoteStatusService teamVoteStatusService;

    public TeamVoteStatusResult requestTeamVoteStatusService(String gameCode, long gameId, long teamId, String sessionKey){
        List<TeamVoteStatus> voteDateStatus = teamVoteStatusService.findTeamVoteDateStatus(teamId, sessionKey);
        List<TeamVoteStatus> rankStatus = teamVoteStatusService.findTeamVoteRankStatus(teamId, sessionKey);


        List<TeamVoteRank> ranks = rankStatus.stream().map(m -> new TeamVoteRank(m.getName()
                , m.getUserId()
                , m.getSessionKey()
                , m.getPoint()
                , m.isMyrank()
                , m.getRownum()
        )).collect(Collectors.toList());

        List<TeamVoteRank> votes = voteDateStatus.stream().map(m -> new TeamVoteRank(m.getName()
                , m.getUserId()
                , m.getSessionKey()
                , m.getVoteDate()
                , m.isMyrank()
                , m.getRownum()
        )).collect(Collectors.toList());
        return new TeamVoteStatusResult(gameCode, gameId, teamId, ranks, votes);
    }

}

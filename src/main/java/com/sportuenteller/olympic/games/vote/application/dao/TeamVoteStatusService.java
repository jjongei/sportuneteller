package com.sportuenteller.olympic.games.vote.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamVoteStatusService {

    @Autowired
    TeamVoteStatusDao teamVoteStatusDao;

    public List<TeamVoteStatus> findTeamVoteRankStatus(long teamId, String sessionKey){
        return teamVoteStatusDao.selectList(teamId, sessionKey, TeamVoteStatusDao.pointOrderBy);
    }

    public List<TeamVoteStatus> findTeamVoteDateStatus(long teamId, String sessionKey){
        return teamVoteStatusDao.selectList(teamId, sessionKey, TeamVoteStatusDao.voteDateOrderBy);
    }



}

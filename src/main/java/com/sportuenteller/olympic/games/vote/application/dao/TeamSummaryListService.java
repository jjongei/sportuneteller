package com.sportuenteller.olympic.games.vote.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSummaryListService {
    @Autowired
    TeamSummaryDao teamSummaryDao;

    public List<TeamSummary> findTeamSummaryList(long gameId){
        return teamSummaryDao.selectList(gameId);
    }
}

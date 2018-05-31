package com.sportuenteller.olympic.games.game.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotePerGameCountListService {

    @Autowired
    VotePerGameCountDao votePerGameCountDao;

    public List<VotePerGameCount> findVotePerGameCountList(){
        return votePerGameCountDao.selectVotePerGameCount();
    }
}

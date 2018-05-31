package com.sportuenteller.olympic.games.vote.application.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalRankService {
    @Autowired
    TotalRankDao totalRankDao;

    public List<TotalRank> findTotalRanByPoint(String sessionKey){
        return totalRankDao.selectList(sessionKey, TotalRankDao.pointOrderBy);
    }

    public List<TotalRank> findTotalRanByMedal(String sessionKey){
        return totalRankDao.selectList(sessionKey, TotalRankDao.medalOrderBy);
    }
}

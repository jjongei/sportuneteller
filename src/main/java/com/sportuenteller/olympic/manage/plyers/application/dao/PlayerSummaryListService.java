package com.sportuenteller.olympic.manage.plyers.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerSummaryListService {

    @Autowired
    PlayerSummaryDao playerSummaryDao;

    public List<PlayerSummary> findPlayerSummaryList(PlayerSummarySearchRequest request){
        List<PlayerSummary> list = this.playerSummaryDao.selectList(request);
        return list;
    }

}

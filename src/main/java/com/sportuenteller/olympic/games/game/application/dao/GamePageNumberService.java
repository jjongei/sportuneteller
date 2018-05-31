package com.sportuenteller.olympic.games.game.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePageNumberService {
    @Autowired
    GamePageNumberDao gamePageNumberDao;

    public int findGamePageNumber(String gameTypeCode, long gameId){
        return gamePageNumberDao.selectGamePageNumber(gameTypeCode, gameId);
    }
}

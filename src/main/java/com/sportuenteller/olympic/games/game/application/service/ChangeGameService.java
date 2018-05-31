package com.sportuenteller.olympic.games.game.application.service;

import com.sportuenteller.olympic.common.code.StatusType;
import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChangeGameService {
    @Autowired
    GameRepository gameRepository;

    @Transactional
    public void terminateGame(long gameId){
        Game game = gameRepository.findById(new GameId(gameId));
        game.terminate();
    }

    @Transactional
    public void cancelTerminateGame(long gameId){
        Game game = gameRepository.findById(new GameId(gameId));
        game.cancelTerminate();
    }

    @Transactional
    public void changeStatus(long gameId){
        Game game = gameRepository.findById(new GameId(gameId));
        game.changeStatus();
    }
}

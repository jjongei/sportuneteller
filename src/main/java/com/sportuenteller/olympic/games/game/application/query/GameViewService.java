package com.sportuenteller.olympic.games.game.application.query;

import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameViewService {
    @Autowired
    GameRepository gameRepository;

    public GameView findGameView(long gameId){
        Game game = gameRepository.findById(new GameId(gameId));

        return game == null ? null : new GameView(game);
    }
}

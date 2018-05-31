package com.sportuenteller.olympic.games.game.application.query;

import com.sportuenteller.olympic.games.game.application.helper.FindGameHelper;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import com.sportuenteller.olympic.games.game.domain.GameSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameViewListService {
    @Autowired
    GameRepository repository;
    @Autowired
    FindGameHelper helper;
    public List<GameView> findGameItems(String gameTypecode){
        return helper.findList(new GameSearchRequest(gameTypecode), repository).stream()
                .map(m -> new GameView(m))
                .collect(Collectors.toList());
    }
}

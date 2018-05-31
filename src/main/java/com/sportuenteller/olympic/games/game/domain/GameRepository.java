package com.sportuenteller.olympic.games.game.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GameRepository {
    Game findById(GameId id);
    List<Game> findList(GameSearchRequest request);
    List<Game> findList(GameSearchRequest request, Sort sort);

    Page<Game> findList(GameSearchRequest request, Pageable pageable);

    long max();

    void save(Game game);
    void remove(Game game);
}

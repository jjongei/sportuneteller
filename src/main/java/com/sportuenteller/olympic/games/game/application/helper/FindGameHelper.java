package com.sportuenteller.olympic.games.game.application.helper;

import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import com.sportuenteller.olympic.games.game.domain.GameSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindGameHelper {
    public List<Game> findList(GameRepository repository){
        return findList(null, repository);
    }
    public List<Game> findList(GameSearchRequest request, GameRepository repository){
        return repository.findList(request);
    }
    public List<Game> findList(GameSearchRequest request, Sort sort, GameRepository repository){
        return repository.findList(request, sort);
    }
    public Page<Game> findPage(GameSearchRequest request, PageRequest pageRequest, GameRepository repository){
        return repository.findList(request, pageRequest);
    }
}

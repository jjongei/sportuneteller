package com.sportuenteller.olympic.games.game.application.query;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.code.Item;
import com.sportuenteller.olympic.common.query.ListPaging;
import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameSearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GameListPaging extends ListPaging<Game, GameView, GameSearchRequest> {

    private List<Item> gameTypeItems = new ArrayList<>();

    public GameListPaging(int currentPage, int offset) {
        super(currentPage, offset);
    }

    protected GameListPaging(Page<Game> page, GameSearchRequest params, int intPageCount) {
        super(page, params, intPageCount);
        this.gameTypeItems = GameType.items();
    }

    protected GameListPaging(Page<Game> page, GameSearchRequest params) {
        this(page, params, 0);
    }

    @Override
    protected GameSearchRequest initParams() {
        return new GameSearchRequest();
    }

    @Override
    protected int initPageCount() {
        return 5;
    }

    @Override
    protected List<GameView> convert(List<Game> contents) {
        return contents.stream().map(m -> new GameView(m)).collect(Collectors.toList());
    }
}


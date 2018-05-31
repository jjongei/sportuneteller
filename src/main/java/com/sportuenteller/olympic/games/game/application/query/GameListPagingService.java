package com.sportuenteller.olympic.games.game.application.query;

import com.sportuenteller.olympic.games.game.application.dao.GamePageNumberDao;
import com.sportuenteller.olympic.games.game.application.helper.FindGameHelper;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import com.sportuenteller.olympic.games.game.domain.GameSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GameListPagingService {
    @Autowired
    GameRepository repository;
    @Autowired
    FindGameHelper helper;
    @Autowired
    GamePageNumberDao gamePageNumberDao;


    public GameListPaging findGamePage(GameSearchRequest request, PageRequest pageRequest) {
        return new GameListPaging(helper.findPage(request, pageRequest, repository)
                , request);
    }

    private GameListPaging findGamePageByOnePageCount(GameSearchRequest request, PageRequest pageRequest) {
        return new GameListPaging(helper.findPage(request, pageRequest, repository)
                , request, 1);
    }

    public GameListPaging findGamePageByGameId(String gameTypeCode, long gameId){
        return this.findGamePageByOnePageCount(new GameSearchRequest(gameTypeCode)
                , new GameListPaging(gamePageNumberDao.selectGamePageNumber(gameTypeCode, gameId)
                        , 1)
                        .getPageRequest());
    }
    public GameListPaging findGamePageByPageNumber(String gameTypeCode, int pageNumber){
        return this.findGamePageByOnePageCount(new GameSearchRequest(gameTypeCode), new GameListPaging(pageNumber, 1).getPageRequest());
    }
}

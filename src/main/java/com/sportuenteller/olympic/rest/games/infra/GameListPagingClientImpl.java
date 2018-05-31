package com.sportuenteller.olympic.rest.games.infra;

import com.sportuenteller.olympic.games.game.application.query.GameListPaging;
import com.sportuenteller.olympic.games.game.application.query.GameListPagingService;
import com.sportuenteller.olympic.games.game.application.query.GameView;
import com.sportuenteller.olympic.rest.games.applicaiton.team.GameListPagingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameListPagingClientImpl implements GameListPagingClient {

    @Autowired
    GameListPagingService service;

    @Override
    public GameItem requestGameListPgaing(String gameTypeCode, long gameId) {
        GameListPaging paging = service.findGamePageByGameId(gameTypeCode, gameId);
        return this.convert(paging, gameId);
    }

    @Override
    public GameItem requestGameListPgaing(String gameTypeCode, int page) {
        GameListPaging paging = service.findGamePageByPageNumber(gameTypeCode, page);
        return this.convert(paging, 0L);
    }

    private GameItem convert(GameListPaging paging, long gameId){
        GameView view = paging.getContents().stream().findFirst().orElse(null);
        if(view == null){
            throw new NotFoundGameException(gameId);
        }
        int currentPage = paging.getCurrentPage();
        int nextPage = paging.isHasNextPage() ? paging.getNextPage() : 1;
        int previousPage = paging.isHasPreviousPage() ? paging.getPreviousPage() : paging.getTotal();


        return new GameItem(view.getGameTypeCode()
                , view.getGameId()
                , view.getGameNameKr()
                , view.getGameNameEn()
                , view.getStatusTypeName()
                , view.getVoteStartDate()
                , view.getVoteEndDate()
                , view.isGroup()
                , currentPage, previousPage, nextPage);
    }
}

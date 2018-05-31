package com.sportuenteller.olympic.rest.games.infra;

import com.sportuenteller.olympic.games.game.application.query.GameView;
import com.sportuenteller.olympic.games.game.application.query.GameViewListService;
import com.sportuenteller.olympic.rest.games.applicaiton.view.GameViewListClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameViewListClientImpl implements GameViewListClient {

    @Autowired
    GameViewListService service;

    @Override
    public List<GameViewListClient.GameItem> requestGameViewList(String gameTypeCode) {

        List<GameView> views = service.findGameItems(gameTypeCode);

        return views.stream()
                .map(m -> new GameViewListClient.GameItem(gameTypeCode
                        , m.getGameId()
                        , m.getGameNameKr()
                        , m.getGameNameEn()
                        , m.getStatusTypeName()
                        , m.getVoteStartDate()
                        , m.getVoteEndDate()))
                .collect(Collectors.toList());
    }
}

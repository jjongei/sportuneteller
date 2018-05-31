package com.sportuenteller.olympic.rest.games.applicaiton.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestGameListService {

    @Autowired
    GameViewListClient client;

    public List<GameResult> findGameList(String gameTypeCode){
        return client.requestGameViewList(gameTypeCode).stream()
                .map(m -> new GameResult(m.getGameCode()
                    , m.getDetailGameSeq()
                    , m.getDetailGameKr()
                    , m.getDetailGameEn()
                    , m.getStatusCode()
                    , m.getVotingSchedule()))
                .collect(Collectors.toList());
    }
}

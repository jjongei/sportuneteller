package com.sportuenteller.olympic.games.vote.infra.impl;

import com.sportuenteller.olympic.games.vote.application.client.PlayerSummaryListClient;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummary;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummaryListService;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummarySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerSummaryListClientImpl implements PlayerSummaryListClient {

    @Autowired
    PlayerSummaryListService service;

    @Override
    public List<PlayerSummaryListClient.PlayerItem> requestPlayerItemLIst(String gameType, String countryCode, boolean male, boolean female, long gameId) {

        List<PlayerSummary> summaries = this.service.findPlayerSummaryList(new PlayerSummarySearchRequest(gameType, countryCode, male, female, gameId));

        return this.convert(summaries);
    }

    @Override
    public List<PlayerSummaryListClient.PlayerItem> requestPlayerItemLIst(List<Long> idList) {
        List<PlayerSummary> summaries = this.service.findPlayerSummaryList(new PlayerSummarySearchRequest(idList));

        return this.convert(summaries);
    }

    @Override
    public List<PlayerItem> requestPlayerItemLIst(long gameId) {
        List<PlayerSummary> summaries = this.service.findPlayerSummaryList(new PlayerSummarySearchRequest(gameId));

        return this.convert(summaries);
    }

    private List<PlayerSummaryListClient.PlayerItem> convert(List<PlayerSummary> summaries){
        return summaries.stream()
                .map(m -> new PlayerSummaryListClient.PlayerItem(m.getPlayerId()
                        , m.getCountryCode()
                        , m.getCountryNameKr()
                        , m.getCountryNameEn()
                        , m.getPlayerName()
                        , m.isSexuality()))
                .collect(Collectors.toList());
    }
}

package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.games.game.application.query.GameView;
import com.sportuenteller.olympic.games.game.application.query.GameViewListService;
import com.sportuenteller.olympic.games.vote.application.client.PlayerSummaryListClient;
import com.sportuenteller.olympic.games.vote.application.helper.FindTeamHelper;
import com.sportuenteller.olympic.games.vote.domain.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamListPageService {

    @Autowired
    TeamRepository repository;
    @Autowired
    FindTeamHelper helper;
    @Autowired
    PlayerSummaryListClient client;
    @Autowired
    GameViewListService gameViewListService;

    public TeamListPage findTeamSetPage(TeamListPageSearchRequest request){
        List<GameView> games = gameViewListService.findGameItems(request.getGameCode());

        if(request.getGameId() == 0L){
            request.setGameId(games.stream().map(m -> m.getGameId()).findFirst().orElse(0L));
        }

        List<PlayerSummaryListClient.PlayerItem> players
                = client.requestPlayerItemLIst(request.getGameCode(), request.getCountryCode(), request.isMale(), request.isFemale(), request.getGameId());

        return new TeamListPage(helper.findList(request.getGameId(), repository)
                , request, games, players);
    }
}

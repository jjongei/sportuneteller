package com.sportuenteller.olympic.rest.games.ui;

import com.sportuenteller.olympic.rest.games.applicaiton.team.RequestVoteTeamListService;
import com.sportuenteller.olympic.rest.games.applicaiton.view.GameResult;
import com.sportuenteller.olympic.rest.games.applicaiton.view.RequestGameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameListRestController {

    @Autowired
    RequestGameListService service;

    @RequestMapping(value = "/vote/{sessionKey}/{gameCode}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<GameResult> getGames(@PathVariable("gameCode") String gameCode, @PathVariable("sessionKey") String sessionKey) {
        return service.findGameList(gameCode);
    }
}

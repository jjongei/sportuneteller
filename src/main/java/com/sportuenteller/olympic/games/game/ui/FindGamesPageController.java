package com.sportuenteller.olympic.games.game.ui;

import com.sportuenteller.olympic.games.game.application.query.GameListPagingService;
import com.sportuenteller.olympic.games.game.application.query.GameListPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vote/games")
public class FindGamesPageController {
    @Autowired
    GameListPagingService service;

    @RequestMapping(method = RequestMethod.GET)
    String getList(Model model){
        GameListPaging intPage = new GameListPaging(1, 20);

        GameListPaging page = service.findGamePage(intPage.getParams(), intPage.getPageRequest());
        model.addAttribute("dossiers", page);
        return "vote/game/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.POST)
    String postSearch(Model model, GameListPaging request){
        GameListPaging page = service.findGamePage(request.getParams(), request.getPageRequest());
        model.addAttribute("dossiers", page);
        return "vote/game/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.GET)
    String postSearch(Model model){
        return "redirect:/vote/games";
    }
}

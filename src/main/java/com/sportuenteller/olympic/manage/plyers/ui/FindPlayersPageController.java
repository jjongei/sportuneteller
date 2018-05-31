package com.sportuenteller.olympic.manage.plyers.ui;

import com.sportuenteller.olympic.manage.plyers.application.query.PlayerListPagingService;
import com.sportuenteller.olympic.manage.plyers.application.query.PlayerListPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("manage/players")
public class FindPlayersPageController {

   @Autowired
   PlayerListPagingService service;

    @RequestMapping(method = RequestMethod.GET)
    String getList(Model model){
        PlayerListPaging intPage = new PlayerListPaging(1, 30);

        PlayerListPaging page = service.findPlayerPage(intPage.getParams(), intPage.getPageRequest());
        model.addAttribute("dossiers", page);
        return "manage/player/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.POST)
    String postSearch(Model model, PlayerListPaging request){
        PlayerListPaging page = service.findPlayerPage(request.getParams(), request.getPageRequest());
        model.addAttribute("dossiers", page);
        return "manage/player/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.GET)
    String postSearch(Model model){
        return "redirect:/manage/players";
    }

}

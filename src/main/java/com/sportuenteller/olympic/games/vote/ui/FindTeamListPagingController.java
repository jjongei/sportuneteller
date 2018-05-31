package com.sportuenteller.olympic.games.vote.ui;

import com.sportuenteller.olympic.games.vote.application.query.TeamListPage;
import com.sportuenteller.olympic.games.vote.application.query.TeamListPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vote/teams")
public class FindTeamListPagingController {

    @Autowired
    TeamListPageService service;

    @RequestMapping(method = RequestMethod.GET)
    String getList(Model model){
        TeamListPage intPage = new TeamListPage();
        TeamListPage page = service.findTeamSetPage(intPage.getParams());
        model.addAttribute("dossiers", page);
        return "vote/team/list";
    }

    @RequestMapping(value ="search", method = RequestMethod.POST)
    String postSearch(Model model, TeamListPage request){
        TeamListPage page = service.findTeamSetPage(request.getParams());
        model.addAttribute("dossiers", page);
        return "vote/team/list";
    }
    @RequestMapping(value ="search", method = RequestMethod.GET)
    String postSearch(Model model){
        return "redirect:/vote/teams";
    }
}

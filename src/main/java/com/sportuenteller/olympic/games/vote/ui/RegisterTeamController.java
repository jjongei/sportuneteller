package com.sportuenteller.olympic.games.vote.ui;

import com.sportuenteller.olympic.games.vote.application.query.TeamListPage;
import com.sportuenteller.olympic.games.vote.application.query.TeamListPageService;
import com.sportuenteller.olympic.games.vote.application.service.RegisterTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vote/teams")
public class RegisterTeamController {
    @Autowired
    RegisterTeamService registerService;
    @Autowired
    TeamListPageService service;

    @RequestMapping(value ="create", method = RequestMethod.POST)
    String postCreate(Model model, TeamListPage request){
        registerService.registerTeam(request.getIdList(), request.getParams().getGameId());

        TeamListPage result = service.findTeamSetPage(request.getParams());
        model.addAttribute("dossiers", result);
        return "vote/team/list";

    }

    @RequestMapping(value ="create/{gameCode}/{gameId}", method = RequestMethod.GET)
    String getCreate(Model model, @PathVariable("gameCode") String gameCode, @PathVariable("gameId") long gameId) {
        registerService.registerTeam(gameId);
        TeamListPage result = service.findTeamSetPage(new TeamListPage(gameCode, gameId).getParams());
        model.addAttribute("dossiers", result);
        return "vote/team/list";
    }

    @RequestMapping(value ="create", method = RequestMethod.GET)
    String getCreate(Model model) {
        return "redirect:/vote/teams";
    }

    @RequestMapping(value ="remove", method = RequestMethod.POST)
    String postRemove(Model model, TeamListPage request){
        registerService.remove(request.getRemoveId());

        model.addAttribute("dossiers", service.findTeamSetPage(request.getParams()));
        return "vote/team/list";
    }

    @RequestMapping(value ="remove", method = RequestMethod.GET)
    String getRemove(Model model) {
        return "redirect:/vote/teams";
    }


    @RequestMapping(value ="result", method = RequestMethod.POST)
    String postResult(Model model, TeamListPage request){
        registerService.registerGameResult(request.getParams().getGameId(), request.getGoldList(), request.getSilverList(), request.getBronzeList());

        TeamListPage result = service.findTeamSetPage(request.getParams());
        model.addAttribute("dossiers", result);
        return "vote/team/list";
    }

    @RequestMapping(value ="reslut", method = RequestMethod.GET)
    String getResult(Model model) {
        return "redirect:/vote/teams";
    }

    @RequestMapping(value ="status", method = RequestMethod.POST)
    String postStatus(Model model, TeamListPage request){
        registerService.changeStatus(request.getParams().getGameId());

        TeamListPage result = service.findTeamSetPage(request.getParams());
        model.addAttribute("dossiers", result);
        return "vote/team/list";
    }

    @RequestMapping(value ="status", method = RequestMethod.GET)
    String getStatus(Model model) {
        return "redirect:/vote/teams";
    }
}

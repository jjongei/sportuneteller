package com.sportuenteller.olympic.rest.games.ui;

import com.sportuenteller.olympic.rest.games.applicaiton.team.RequestVoteTeamListService;
import com.sportuenteller.olympic.rest.games.applicaiton.team.TargetResult;
import com.sportuenteller.olympic.rest.games.applicaiton.vote.RequestVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteRequestRestController {
    @Autowired
    RequestVoteService service;
    @Autowired
    RequestVoteTeamListService voteTeamListService;

    @RequestMapping(value = "/vote/{sessionKey}/{gameCode}/{detailGameSeq}/{voteTeamSeq}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TargetResult patchVote(@PathVariable("sessionKey") String sessionKey, @PathVariable("gameCode") String gameCode, @PathVariable("detailGameSeq") Long detailGameSeq
            , @PathVariable("voteTeamSeq") Long voteTeamSeq) {


        service.requestVote(detailGameSeq, voteTeamSeq, sessionKey);

        return voteTeamListService.findVoteTeamList(sessionKey, gameCode, detailGameSeq);


    }
}

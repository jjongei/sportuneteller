package com.sportuenteller.olympic.rest.games.ui;

import com.sportuenteller.olympic.rest.games.applicaiton.team.RequestVoteTeamListService;
import com.sportuenteller.olympic.rest.games.applicaiton.team.TargetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteTeamListRestController {
    @Autowired
    RequestVoteTeamListService service;

    @RequestMapping(value = "/vote/{sessionKey}/{gameCode}/{detailGameSeq}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TargetResult getVoteTarget(@PathVariable("sessionKey") String sessionKey
            , @PathVariable("gameCode") String gameCode, @PathVariable("detailGameSeq") Long detailGameSeq) {
        return service.findVoteTeamList(sessionKey, gameCode, detailGameSeq);
    }

    @RequestMapping(value = "/vote/{sessionKey}/{gameCode}/page/{page}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TargetResult getVoteTargetByPaging(@PathVariable("sessionKey") String sessionKey, @PathVariable("gameCode") String gameCode, @PathVariable("page") int page) {
        return service.findVoteTeamList(sessionKey, gameCode, page);
    }
}

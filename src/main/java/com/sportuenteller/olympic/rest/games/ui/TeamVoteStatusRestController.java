package com.sportuenteller.olympic.rest.games.ui;

import com.sportuenteller.olympic.rest.games.applicaiton.status.RequestTeamVoteStatusService;
import com.sportuenteller.olympic.rest.games.applicaiton.status.TeamVoteStatusResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamVoteStatusRestController {

    @Autowired
    RequestTeamVoteStatusService requestTeamVoteStatusService;

    @RequestMapping(value = "/vote/status/{sessionKey}/{gameCode}/{detailGameSeq}/{teamSeq}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TeamVoteStatusResult voteStatus(@PathVariable("sessionKey") String sessionKey, @PathVariable("gameCode") String gameCode, @PathVariable("detailGameSeq") Long detailGameSeq
            , @PathVariable("teamSeq") Long teamSeq) {
        return requestTeamVoteStatusService.requestTeamVoteStatusService(gameCode
            , detailGameSeq
            , teamSeq
            , sessionKey);
    }
}

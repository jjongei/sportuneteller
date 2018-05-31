package com.sportuenteller.olympic.rest.games.ui;

import com.sportuenteller.olympic.rest.games.applicaiton.count.RequestVotesPerGameCountService;
import com.sportuenteller.olympic.rest.games.applicaiton.count.VotesCountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VotePerGameCountRestController {
    @Autowired
    RequestVotesPerGameCountService service;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<VotesCountResult> getCount() {
        return service.findVotePerGameCount();
    }
}

package com.sportuenteller.olympic.rest.games.applicaiton.vote;

import com.sportuenteller.olympic.games.vote.application.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestVoteService {

    @Autowired
    VoteService voteService;

    public void requestVote(long gameId, long teamId, String sessionKey){
        voteService.vote(gameId, teamId, sessionKey);
    }
}

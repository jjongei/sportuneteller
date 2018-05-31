package com.sportuenteller.olympic.rest.games.applicaiton.count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestVotesPerGameCountService {

    @Autowired
    VotesPerGameCountListClient client;

    public List<VotesCountResult> findVotePerGameCount(){
        return client.requestVotePerGameCountList().stream()
                .map(m -> new VotesCountResult(m.getGameTypeCode(), m.getCount(), m.isHotGame(), m.isReward(), m.isVoteAvailable()))
                .collect(Collectors.toList());
    }
}

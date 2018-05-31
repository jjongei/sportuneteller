package com.sportuenteller.olympic.rest.games.infra;

import com.sportuenteller.olympic.games.game.application.dao.VotePerGameCount;
import com.sportuenteller.olympic.games.game.application.dao.VotePerGameCountListService;
import com.sportuenteller.olympic.rest.games.applicaiton.count.VotesPerGameCountListClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VotePerGameCountListClientImpl implements VotesPerGameCountListClient {

    @Autowired
    VotePerGameCountListService service;

    @Override
    public List<VotesPerGameCountListClient.GameCountItem> requestVotePerGameCountList() {
        List<VotePerGameCount> counts = service.findVotePerGameCountList();
        return counts.stream().map(m -> new VotesPerGameCountListClient.GameCountItem(m.getGameTypeCode(), m.getCount(), m.isHotGame(), m.isReward(), m.isVoteAvailable())).collect(Collectors.toList());
    }
}

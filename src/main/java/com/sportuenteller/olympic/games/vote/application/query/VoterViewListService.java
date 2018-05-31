package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.games.vote.application.helper.FindVoterHelper;
import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterRepository;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterViewListService {
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    FindVoterHelper findVoterHelper;

    public List<VoterView> findVoterViewList(){
        List<Voter> voters = findVoterHelper.findList(new VoterSearchRequest(), voterRepository);
        return voters.stream().map(m -> new VoterView(m)).collect(Collectors.toList());
    }
}

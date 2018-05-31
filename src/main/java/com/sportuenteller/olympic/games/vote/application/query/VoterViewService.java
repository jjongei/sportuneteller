package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterId;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterViewService {
    @Autowired
    VoterRepository voterRepository;

    public VoterView findVoterView(String sessionKey){
        Voter voter = voterRepository.findById(new VoterId(sessionKey));
        if(voter == null){
            return null;
        }
        return new VoterView(voter);
    }
}

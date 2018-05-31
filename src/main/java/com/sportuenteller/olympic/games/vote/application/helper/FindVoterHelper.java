package com.sportuenteller.olympic.games.vote.application.helper;

import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterRepository;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterSearchRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindVoterHelper {
    public List<Voter> findList(VoterSearchRequest request, VoterRepository repository){
        return repository.findList(request);
    }
}

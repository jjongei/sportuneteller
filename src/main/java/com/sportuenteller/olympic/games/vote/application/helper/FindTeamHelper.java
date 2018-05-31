package com.sportuenteller.olympic.games.vote.application.helper;

import com.sportuenteller.olympic.games.vote.domain.team.Team;
import com.sportuenteller.olympic.games.vote.domain.team.TeamRepository;
import com.sportuenteller.olympic.games.vote.domain.team.TeamSearchRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindTeamHelper {
    public List<Team> findList(Long gameId, TeamRepository repository){
        return this.findList(new TeamSearchRequest(gameId), repository);
    }
    public List<Team> findList(TeamSearchRequest request, TeamRepository repository){
        return repository.findList(request);
    }
}

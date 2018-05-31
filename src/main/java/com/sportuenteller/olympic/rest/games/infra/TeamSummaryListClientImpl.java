package com.sportuenteller.olympic.rest.games.infra;

import com.sportuenteller.olympic.games.vote.application.dao.TeamSummary;
import com.sportuenteller.olympic.games.vote.application.dao.TeamSummaryListService;
import com.sportuenteller.olympic.rest.games.applicaiton.team.TeamSummaryListClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamSummaryListClientImpl implements TeamSummaryListClient{
    @Autowired
    TeamSummaryListService service;

    @Override
    public List<TeamIteam> requestTeamSummaryList(long gameId, String sessionKey) {
        List<TeamSummary> summaries = service.findTeamSummaryList(gameId);
        return this.convert(summaries, sessionKey);
    }

    private List<TeamIteam> convert(List<TeamSummary> summaries, String sessionKey){
        if(summaries == null){
            Collections.emptyList();
        }
        return summaries.stream()
                .map(m -> new TeamIteam(m.getTeamId()
                    , m.getCountryCode()
                    , m.getCountryNameKr()
                    , m.getCountryNameEn()
                    , m.getPlayers() != null ? new ArrayList<String>(m.getPlayers()) : null
                    , m.getMedalType() != null ? m.getMedalType().name() : null
                    , m.isAvailable()
                    , m.getVoters() != null ? new ArrayList<String>(m.getVoters()
                        .stream().map(d -> d.getVoters()).flatMap(f -> f.stream()).collect(Collectors.toList())) : null
                    , sessionKey))
                .collect(Collectors.toList());
    }
}

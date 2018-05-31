package com.sportuenteller.olympic.rest.games.applicaiton.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestVoteTeamListService {

    @Autowired
    GameListPagingClient gameListPagingClient;

    @Autowired
    TeamSummaryListClient TeamSummaryListClient;

    public TargetResult findVoteTeamList(String sessionKey, String gameCode, long gameId){
        TargetResult target = this.convert(gameListPagingClient.requestGameListPgaing(gameCode, gameId));
        return this.convert(target, TeamSummaryListClient.requestTeamSummaryList(gameId, sessionKey));
    }

    public TargetResult findVoteTeamList(String sessionKey, String gameCode, int page){
        TargetResult target = this.convert(gameListPagingClient.requestGameListPgaing(gameCode, page));

        return this.convert(target, TeamSummaryListClient.requestTeamSummaryList(target.getDetailGameSeq(), sessionKey));
    }

    private TargetResult convert(TargetResult teamResult, List<TeamSummaryListClient.TeamIteam> items){
        List<TeamResult> teams = new ArrayList<>();
        if(items != null){
            for(TeamSummaryListClient.TeamIteam item : items){
                teams.add(new TeamResult(item.getTeamSeq()
                        , item.getCountryCode()
                        , item.getCountryNameKr()
                        , item.getCountryNameEn()
                        , item.getPlayers()
                        , item.getMedalCode()
                        , item.getVote()
                        , item.isAvailable()
                        , item.isMyVote()));
            }
        }
        teamResult.setTeams(teams);
        return teamResult;
    }

    private TargetResult convert(GameListPagingClient.GameItem item){
        return new TargetResult(item.getGameCode()
            , item.getDetailGameSeq()
            , item.getDetailGameKr()
            , item.getDetailGameEn()
            , item.getStatusCode()
            , item.getVotingSchedule()
            , item.isGroupGame()
            , item.getCurrentPage()
            , item.getPreviousPage()
            , item.getNextPage());
    }


}

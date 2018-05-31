package com.sportuenteller.olympic.games.vote.infra.dao;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.vote.application.dao.TeamSummary;
import com.sportuenteller.olympic.games.vote.application.dao.TeamSummaryDao;
import com.sportuenteller.olympic.games.vote.application.dao.VoteTeamSummary;
import com.sportuenteller.olympic.games.vote.domain.team.QTeam;
import com.sportuenteller.olympic.games.vote.domain.team.QTeamPlayer;
import com.sportuenteller.olympic.games.vote.domain.voter.QVoteTeam;
import com.sportuenteller.olympic.games.vote.domain.voter.QVoter;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JpaTeamSummaryDao extends QueryDslRepositorySupport implements TeamSummaryDao {
    public JpaTeamSummaryDao() {
        super(TeamSummary.class);
    }

    public List<TeamSummary> selectList(long gameId){

        QTeam qTeam = QTeam.team;
        QTeamPlayer qTeamPlayer = QTeamPlayer.teamPlayer;
        QVoteTeam qVoteTeam = QVoteTeam.voteTeam;
        QVoter qVoter = QVoter.voter;

        Map<Long, TeamSummary> teamSummaryMap = this.from(qTeam)
                .leftJoin(qTeam.teamPlayers, qTeamPlayer)
                .where(qTeam.gameId.eq(new GameId(gameId)))
                .transform(GroupBy.groupBy(qTeam.id.value)
                        .as(Projections.bean(TeamSummary.class
                                , qTeam.id.value.as("teamId")
                                , qTeam.gameId.value.as("gameId")
                                , qTeam.countries.code.as("countryCode")
                                , qTeam.countries.nameEn.as("countryNameKr")
                                , qTeam.countries.nameKr.as("countryNameEn")
                                , qTeam.medalType.as("medalType")
                                , qTeam.vote.as("vote")
                                , qTeam.votingAvailable.as("available")
                                , GroupBy.set(qTeamPlayer.player.name).as("players")
                        ))
                );

        List<TeamSummary> summaries = new ArrayList<TeamSummary>(teamSummaryMap.values());

        Map<Long, VoteTeamSummary> voterTeamSummaryMap = this.from(qVoter)
                .leftJoin(qVoter.voteTeams, qVoteTeam)
                .transform(GroupBy.groupBy(qVoteTeam.teamId.value)
                    .as(Projections.bean(VoteTeamSummary.class
                            , qVoteTeam.teamId.value.as("teamId")
                            , GroupBy.set(qVoter.id.value).as("voters")
                    ))
                );

        List<VoteTeamSummary> voterSummaries = new ArrayList<VoteTeamSummary>(voterTeamSummaryMap.values());

        summaries.stream().forEach(e -> e.setVoters(voterSummaries));

        return summaries;
    }
}

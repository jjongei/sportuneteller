package com.sportuenteller.olympic.games.vote.infra.dao;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.sportuenteller.olympic.games.game.domain.QGame;
import com.sportuenteller.olympic.games.vote.application.rank.MyStatusGame;
import com.sportuenteller.olympic.games.vote.application.rank.MyStatusGameDao;
import com.sportuenteller.olympic.games.vote.domain.team.QTeam;
import com.sportuenteller.olympic.games.vote.domain.team.QTeamPlayer;
import com.sportuenteller.olympic.games.vote.domain.team.TeamId;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JpaMyStatusGameDao extends QueryDslRepositorySupport implements MyStatusGameDao {

    public JpaMyStatusGameDao() {
        super(MyStatusGame.class);
    }

    @Override
    public List<MyStatusGame> selectList(List<Long> idList) {

        if(idList == null || idList.isEmpty())
            return Collections.emptyList();

        QTeam qTeam = QTeam.team;
        QTeamPlayer qTeamPlayer = QTeamPlayer.teamPlayer;
        QGame qGame = QGame.game;

        Map<Long, MyStatusGame> map = this.from(qTeam, qGame)
                .leftJoin(qTeam.teamPlayers, qTeamPlayer)
                .where(qTeam.gameId.eq(qGame.id).and(qTeam.id.in(idList.stream().map(m -> new TeamId(m)).collect(Collectors.toList()))))
                .transform(GroupBy.groupBy(qTeam.id.value)
                    .as(Projections.bean(MyStatusGame.class
                        , qGame.gameType.as("gameTypeCode")
                        , qGame.id.value.as("gameId")
                        , qGame.name.nameKr.as("gameGameKr")
                        , qGame.name.nameEn.as("gameGameEn")
                        , qGame.group.as("group")
                        , qTeam.id.value.as("teamId")
                        , qTeam.countries.code.as("countryCode")
                        , qTeam.countries.nameKr.as("countryNameKr")
                        , qTeam.countries.nameEn.as("countryNameEn")
                        , qTeam.medalType.as("medalCode")
                        , qGame.statusType.as("statusType")
                        , GroupBy.set(qTeamPlayer.player.name).as("players")
                    ))
                );

        List<MyStatusGame> myStatusGames = new ArrayList<MyStatusGame>(map.values());
        return myStatusGames;
    }
}

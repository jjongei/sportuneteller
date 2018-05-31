package com.sportuenteller.olympic.manage.plyers.infra.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.sportuenteller.olympic.manage.countries.domain.QCountry;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummary;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummaryDao;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummarySearchRequest;
import com.sportuenteller.olympic.manage.plyers.domain.QParticipationGame;
import com.sportuenteller.olympic.manage.plyers.domain.QPlayer;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class JpaPlayerSummaryDao extends QueryDslRepositorySupport implements PlayerSummaryDao{

    public JpaPlayerSummaryDao() {
        super(PlayerSummary.class);
    }

    @Override
    public List<PlayerSummary> selectList(PlayerSummarySearchRequest request) {


        QPlayer qPlayer = QPlayer.player;
        QParticipationGame qParticipationGame = QParticipationGame.participationGame;
        QCountry qCountry = QCountry.country;

        BooleanBuilder builder = request.buildDynamicQuery(qPlayer, qCountry, qParticipationGame);
        if(builder == null){
            return Collections.emptyList();
        }

        JPQLQuery<?> query = this.from(qPlayer, qCountry)
                .leftJoin(qPlayer.participationGames, qParticipationGame)
                .distinct()
                .select(Projections.bean(PlayerSummary.class
                        , qPlayer.id.value.as("playerId")
                        , qPlayer.gameType.as("gameType")
                        , qCountry.id.value.as("countryCode")
                        , qCountry.name.nameKr.as("countryNameKr")
                        , qCountry.name.nameEn.as("countryNameEn")
                        , qPlayer.name.as("playerName")
                        , qPlayer.sexuality.as("sexuality")))
                .where(qPlayer.countryId.eq(qCountry.id).and(builder))
                .orderBy(qPlayer.gameType.asc(), qCountry.id.value.asc(), qPlayer.sexuality.asc());


        List<PlayerSummary> list = (List<PlayerSummary>) query.fetch();
        if(list == null ){
            return Collections.emptyList();
        }
        return list;
    }
}

package com.sportuenteller.olympic.games.vote.domain.team;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.query.SearchRequest;
import com.sportuenteller.olympic.games.game.domain.GameId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamSearchRequest extends SearchRequest<QTeam> {
    private Long gameId;

    public TeamSearchRequest(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public BooleanBuilder buildDynamicQuery(QTeam qClass) {
        BooleanBuilder builder = new BooleanBuilder();
        if(gameId != null){
            builder.and(qClass.gameId.eq(new GameId(this.gameId)));
        }
        return builder;
    }
}

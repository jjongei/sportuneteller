package com.sportuenteller.olympic.games.game.domain;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.query.SearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
public class GameSearchRequest extends SearchRequest<QGame>{
    private String gameType;

    public GameSearchRequest(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public BooleanBuilder buildDynamicQuery(QGame qClass) {
        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.hasText(this.gameType)){
            builder.and(qClass.gameType.eq(GameType.convert(this.gameType)));
        }

        return builder;
    }
}

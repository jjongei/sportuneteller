package com.sportuenteller.olympic.games.vote.domain.voter;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.query.SearchRequest;
import com.sportuenteller.olympic.games.game.domain.GameId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class VoterSearchRequest extends SearchRequest<QVoter> {

    private String name;
    private Long gameId;
    private List<Long> teamIdList;

    public VoterSearchRequest(List<Long> teamIdList) {
        this.teamIdList = teamIdList;
    }

    public VoterSearchRequest(long gameId) {
        this.gameId = gameId;
    }

    @Override
    public BooleanBuilder buildDynamicQuery(QVoter qClass) {
        BooleanBuilder builder = new BooleanBuilder();
        if(this.teamIdList != null){
            if(this.teamIdList.isEmpty()){
                return null;
            }
            builder.and(qClass.voteTeams.any().teamId.value.in(this.teamIdList));
        }

        if(StringUtils.hasText(this.name)){
            builder.and(qClass.name.contains(this.name));
        }

        if(this.gameId != null){
            builder.and(qClass.voteTeams.any().gameId.eq(new GameId(this.gameId)));
        }

        return builder;
    }

}

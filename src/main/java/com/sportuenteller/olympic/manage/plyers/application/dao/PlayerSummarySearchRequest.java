package com.sportuenteller.olympic.manage.plyers.application.dao;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.manage.countries.domain.QCountry;
import com.sportuenteller.olympic.manage.plyers.domain.QParticipationGame;
import com.sportuenteller.olympic.manage.plyers.domain.QPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerSummarySearchRequest{

    private String gameType;
    private String countryCode;
    private boolean male = true;
    private boolean female = true;
    private Long gameId;
    private List<Long> idList;

    public PlayerSummarySearchRequest(String gameType, String countryCode, boolean male, boolean female, long gameId) {
        this.gameType = gameType;
        this.countryCode = countryCode;
        this.male = male;
        this.female = female;
        this.gameId = gameId;
    }

    public PlayerSummarySearchRequest(List<Long> idList) {
        this.idList = idList;
    }

    public PlayerSummarySearchRequest(long gameId) {
        this.gameId = gameId;
    }

    public BooleanBuilder buildDynamicQuery(QPlayer qPlayer, QCountry qCountry, QParticipationGame qParti) {
        BooleanBuilder builder = new BooleanBuilder();

        if(!male && !female){
            return null;
        }
        if(this.idList != null){
            if(this.idList.isEmpty()){
                return null;
            }
            builder.and(qPlayer.id.value.in(idList));
        }

        if(StringUtils.hasText(this.gameType)){
            builder.and(qPlayer.gameType.eq(GameType.convert(this.gameType)));
        }

        if(StringUtils.hasText(this.countryCode)){
            builder.and(qCountry.id.value.eq(this.countryCode));
        }

        if(this.male && !this.female){
            builder.and(qPlayer.sexuality.eq(true));
        }else if(!this.male && this.female){
            builder.and(qPlayer.sexuality.eq(false));
        }

        if(gameId != null){
            builder.and(qParti.gameId.eq(this.gameId));
        }
        return builder;
    }
}

package com.sportuenteller.olympic.manage.plyers.domain;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.query.SearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerSearchRequest extends SearchRequest<QPlayer> {

    private String gameType;
    private String countryCode;
    private String name;
    private boolean male = true;
    private boolean female = true;
    private List<Long> idList;
    private Long gameId;


    @Override
    public BooleanBuilder buildDynamicQuery(QPlayer qClass) {
        if(!this.checkSexuality()){
            return null;
        }

        BooleanBuilder builder = new BooleanBuilder();
        if(this.idList != null){
            if(this.idList.isEmpty()){
                return null;
            }
            builder.and(qClass.id.value.in(idList));
        }

        if(StringUtils.hasText(this.gameType)){
            builder.and(qClass.gameType.eq(GameType.convert(this.gameType)));
        }

        if(StringUtils.hasText(this.countryCode)){
            builder.and(qClass.countryId.value.eq(this.countryCode));
        }

        if(StringUtils.hasText(this.name)){
            builder.and(qClass.name.contains(this.name));
        }

        if(this.male && !this.female){
            builder.and(qClass.sexuality.eq(true));
        }else if(!this.male && this.female){
            builder.and(qClass.sexuality.eq(false));
        }

        if(gameId != null){
            builder.and(qClass.participationGames.any().gameId.eq(this.gameId));
        }

        return builder;
    }

    private boolean checkSexuality(){
        if(!male && !female){
            return false;
        }
        return true;
    }
}


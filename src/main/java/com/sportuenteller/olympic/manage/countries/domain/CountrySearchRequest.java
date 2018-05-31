package com.sportuenteller.olympic.manage.countries.domain;

import com.querydsl.core.BooleanBuilder;
import com.sportuenteller.olympic.common.query.SearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class CountrySearchRequest extends SearchRequest<QCountry>{

    private String name;
    private Boolean available;
    private List<String> idList;

    public CountrySearchRequest(List<String> idList) {
        this.idList = idList;
    }

    public CountrySearchRequest(Boolean available) {
        this.available = available;
    }

    @Override
    public BooleanBuilder buildDynamicQuery(QCountry qClass) {
        BooleanBuilder builder = new BooleanBuilder();

        if(this.idList != null){
            if(this.idList.isEmpty()){
                return null;
            }
            builder.and(qClass.id.value.in(idList));
        }

        if(StringUtils.hasText(this.name)){
            builder.and(qClass.name.nameEn.contains(this.name).or(qClass.name.nameKr.contains(this.name)));
        }
        if(this.available != null){
            builder.and(qClass.available.eq(this.available));
        }
        return builder;
    }


}

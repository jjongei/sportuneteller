package com.sportuenteller.olympic.manage.countries.application.query;

import com.sportuenteller.olympic.common.query.ListPaging;
import com.sportuenteller.olympic.manage.countries.domain.Country;
import com.sportuenteller.olympic.manage.countries.domain.CountrySearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CountryListPaging extends ListPaging<Country, CountryView, CountrySearchRequest> {

    public CountryListPaging(int currentPage, int offset) {
        super(currentPage, offset);
    }
    protected CountryListPaging(Page<Country> page, CountrySearchRequest params) {
        super(page, params, 0);
    }


    @Override
    protected CountrySearchRequest initParams() {
        return new CountrySearchRequest(true);
    }

    @Override
    protected int initPageCount() {
        return 5;
    }

    @Override
    protected List<CountryView> convert(List<Country> contents) {
        return contents.stream().map(m -> new CountryView(m)).collect(Collectors.toList());
    }
}

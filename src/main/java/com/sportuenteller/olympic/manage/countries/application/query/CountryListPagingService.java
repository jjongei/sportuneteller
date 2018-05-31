package com.sportuenteller.olympic.manage.countries.application.query;

import com.sportuenteller.olympic.manage.countries.application.helper.FindCountryHelper;
import com.sportuenteller.olympic.manage.countries.domain.CountryRepository;
import com.sportuenteller.olympic.manage.countries.domain.CountrySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CountryListPagingService {

    @Autowired
    CountryRepository repository;
    @Autowired
    FindCountryHelper helper;

    public CountryListPaging findCountryPage(CountrySearchRequest request, PageRequest pageRequest){
        return new CountryListPaging(helper.findPage(request, pageRequest, repository), request);
    }
}

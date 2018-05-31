package com.sportuenteller.olympic.manage.countries.application.query;

import com.sportuenteller.olympic.manage.countries.application.helper.FindCountryHelper;
import com.sportuenteller.olympic.manage.countries.domain.CountryRepository;
import com.sportuenteller.olympic.manage.countries.domain.CountrySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryViewListService {

    @Autowired
    CountryRepository repository;
    @Autowired
    FindCountryHelper helper;

    public List<CountryView> findCountryItems(List<String> idList){
        return helper.findList(new CountrySearchRequest(idList), repository).stream()
                .map(m -> new CountryView(m))
                .collect(Collectors.toList());
    }
}

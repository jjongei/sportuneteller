package com.sportuenteller.olympic.manage.countries.application.helper;

import com.sportuenteller.olympic.manage.countries.domain.Country;
import com.sportuenteller.olympic.manage.countries.domain.CountryRepository;
import com.sportuenteller.olympic.manage.countries.domain.CountrySearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindCountryHelper {

    public List<Country> findList(CountryRepository repository){
        return findList(null, repository);
    }
    public List<Country> findList(CountrySearchRequest request, CountryRepository repository){

        List<Country> list =repository.findList(request);
        return list;
    }
    public Page<Country> findPage(CountrySearchRequest request, PageRequest pageRequest, CountryRepository repository){
        return repository.findList(request, pageRequest);
    }
}

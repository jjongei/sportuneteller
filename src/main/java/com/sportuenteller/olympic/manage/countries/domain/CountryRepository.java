package com.sportuenteller.olympic.manage.countries.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CountryRepository {
    Country findById(CountryId id);
    List<Country> findList(CountrySearchRequest request);
    List<Country> findList(CountrySearchRequest request, Sort sort);

    Page<Country> findList(CountrySearchRequest request, Pageable pageable);

    void save(Country country);
    void remove(Country country);
}

package com.sportuenteller.olympic.manage.countries.infra;

import com.sportuenteller.olympic.common.query.QueryDslBuilder;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.manage.countries.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCountryRepository extends QueryDslBuilder<Country> implements CountryRepository {

    public JpaCountryRepository() {
        super(Country.class);
    }

    @Override
    public Country findById(CountryId id) {
        QCountry qCountry = QCountry.country;
        return this.from(qCountry).where(qCountry.id.eq(id)).fetchOne();
    }
    @Override
    public List<Country> findList(CountrySearchRequest request) {
       return this.findList(request, this.getSort());
    }
    @Override
    public List<Country> findList(CountrySearchRequest request, Sort sort) {
        return super.findList(QCountry.country, request, sort);
    }

    @Override
    public Page<Country> findList(CountrySearchRequest request, Pageable pageable) {
        return super.findList(QCountry.country, request, pageable);
    }

    @Override
    public void save(Country country) {
        this.getEntityManager().persist(country);
    }

    @Override
    public void remove(Country country) {
        this.getEntityManager().remove(country);
    }
    @Override
    protected Sort getSort() {
        return SortBuilder.build(false, "id.value");
    }
}

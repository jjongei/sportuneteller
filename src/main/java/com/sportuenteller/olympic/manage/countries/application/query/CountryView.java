package com.sportuenteller.olympic.manage.countries.application.query;

import com.sportuenteller.olympic.manage.countries.domain.Country;
import lombok.Getter;

@Getter
public class CountryView {
    private String countryCode;
    private String countryNameKr;
    private String countryNameEn;
    private boolean available;

    protected CountryView(Country country){
        this.countryCode = country.getId().getValue();
        this.countryNameKr = country.getName().getNameKr();
        this.countryNameEn = country.getName().getNameEn();
        this.available = country.getAvailable() != null ? country.getAvailable() : false;
    }
}

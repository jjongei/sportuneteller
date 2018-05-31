package com.sportuenteller.olympic.manage.countries.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class CountryId implements Serializable {

    @Column(name = "country_code", columnDefinition = "char(2)", nullable = false)
    private String value;

    protected CountryId(){}

    public CountryId(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryId that = (CountryId) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

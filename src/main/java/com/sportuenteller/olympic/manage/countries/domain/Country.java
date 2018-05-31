package com.sportuenteller.olympic.manage.countries.domain;

import com.sportuenteller.olympic.common.model.Name;
import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="country")
public class Country {

    @EmbeddedId
    private CountryId id;

    @Embedded
    private Name name;

    @Column(name="available", columnDefinition="char(1)", nullable=false)
    private Boolean available;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Country(){}

    public Country(CountryId id, Name name, Boolean available) {
        this.setId(id);
        this.setName(name);
        this.setAvailable(available);

        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }

    public void modifyCountry(Name name, Boolean available){
        this.name = name;
        this.available = available;
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }

    private void setId(CountryId id) {
        if(id == null) throw new IllegalArgumentException("Country ID");
        this.id = id;
    }

    private void setName(Name name) {
        if(name == null) throw new IllegalArgumentException("Name");
        this.name = name;
    }

    private void setAvailable(Boolean available) {
        this.available = available != null ? available : false;
    }
}

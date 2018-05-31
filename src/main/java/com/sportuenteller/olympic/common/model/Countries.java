package com.sportuenteller.olympic.common.model;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class Countries {

    @Column(name = "country_code", columnDefinition = "char(2)", nullable = false)
    private String code;

    @Column(name = "country_name_kr", columnDefinition = "nvarchar(128)", nullable = false)
    private String nameKr;
    @Column(name = "country_name_en", columnDefinition = "nvarchar(128)", nullable = false)
    private String nameEn;


    protected Countries() {
    }

    public Countries(String code, String nameKr, String nameEn) {
        this.setCode(code);
        this.setNameKr(nameKr);
        this.setNameEn(nameEn);
    }

    private void setCode(String code) {
        if(code == null) throw new IllegalArgumentException("Country Code");
        this.code = code;
    }

    private void setNameKr(String nameKr) {
        if(nameKr == null) throw new IllegalArgumentException("Country Name Kr");
        this.nameKr = nameKr;
    }

    private void setNameEn(String nameEn) {
        if(nameEn == null) throw new IllegalArgumentException("Country Name En");
        this.nameEn = nameEn;
    }


}

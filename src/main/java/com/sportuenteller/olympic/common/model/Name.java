package com.sportuenteller.olympic.common.model;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class Name {

    @Column(name = "name_kr", columnDefinition = "nvarchar(128)", nullable = false)
    private String nameKr;
    @Column(name = "name_en", columnDefinition = "nvarchar(128)", nullable = false)
    private String nameEn;

    protected Name() {
    }
    public Name(String nameKr, String nameEn) {
        this.setNameKr(nameKr);
        this.setNameEn(nameEn);
    }

    private void setNameKr(String nameKr) {
        if(nameKr == null) throw new IllegalArgumentException("Name Kr");
        this.nameKr = nameKr;
    }

    private void setNameEn(String nameEn) {
        if(nameEn == null) throw new IllegalArgumentException("Name En");
        this.nameEn = nameEn;
    }
}

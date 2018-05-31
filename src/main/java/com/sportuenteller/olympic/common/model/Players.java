package com.sportuenteller.olympic.common.model;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class Players {

    @Column(name = "player_id", columnDefinition = "bigint", nullable = false)
    private Long id;

    @Column(name = "player_name", columnDefinition = "nvarchar(128)", nullable = false)
    private String name;

    protected Players(){}

    public Players(Long id, String name) {
       this.setId(id);
       this.setName(name);
    }

    private void setId(Long id) {
        if(id == null) throw new IllegalArgumentException("Player ID");
        this.id = id;
    }

    private void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Name");
        this.name = name;
    }
}

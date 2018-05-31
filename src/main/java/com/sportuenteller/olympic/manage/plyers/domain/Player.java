package com.sportuenteller.olympic.manage.plyers.domain;


import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.manage.countries.domain.CountryId;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="player")
public class Player {

    @EmbeddedId
    private PlayerId id;

    @Column(name = "game_type", columnDefinition = "char(4)", nullable = false)
    private GameType gameType;

    @Embedded
    private CountryId countryId;

    @Column(name = "country_reference", columnDefinition = "char(3)", nullable = false)
    private String countryReference;

    @Column(name = "name", columnDefinition = "nvarchar(128)", nullable = false)
    private String name;

    @Column(name="sexuality", columnDefinition="char(1)", nullable=false)
    private Boolean sexuality;


    @ElementCollection
    @CollectionTable(name = "participation_game", joinColumns = @JoinColumn(name = "player_id"))
    @OrderColumn(name = "line_idx")
    private List<ParticipationGame> participationGames;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Player(){}

    public Player(PlayerId id, GameType gameType, CountryId countryId, String countryReference, String name, Boolean sexuality, List<ParticipationGame> participationGames) {
        this.setId(id);
        this.setGameType(gameType);
        this.setCountryId(countryId);
        this.setCountryReference(countryReference);
        this.setName(name);
        this.setSexuality(sexuality);
        this.setParticipationGames(participationGames);

        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }

    public void modifyPlayer(GameType gameType, CountryId countryId, String countryReference, String name, Boolean sexuality, List<ParticipationGame> participationGames){
        this.setGameType(gameType);
        this.setCountryId(countryId);
        this.setCountryReference(countryReference);
        this.setName(name);
        this.setSexuality(sexuality);
        this.setParticipationGames(participationGames);

        this.lastModifiedDate = DateUtil.getCurrentDate();
    }

    private void verifyAtLeastOneOrMoreParticipationGames(List<ParticipationGame> participationGames){
        if(participationGames == null || participationGames.isEmpty()){
            throw new IllegalArgumentException("ParticipationGames");
        }
    }

    private void setId(PlayerId id) {
        if(id == null) throw new IllegalArgumentException("Player ID");
        this.id = id;
    }

    private void setGameType(GameType gameType) {
        if(gameType == null) throw new IllegalArgumentException("Game Type");
        this.gameType = gameType;
    }

    private void setCountryId(CountryId countryId) {
        if(countryId == null) throw new IllegalArgumentException("Country Code");
        this.countryId = countryId;
    }

    public void setCountryReference(String countryReference) {
        if(countryReference == null) throw new IllegalArgumentException("Country Reference");
        this.countryReference = countryReference;
    }

    private void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Name");
        this.name = name;
    }

    private void setSexuality(Boolean sexuality) {
        this.sexuality = sexuality != null ? sexuality : false;
    }

    public void setParticipationGames(List<ParticipationGame> participationGames) {
        this.verifyAtLeastOneOrMoreParticipationGames(participationGames);
        this.participationGames = participationGames;
    }
}

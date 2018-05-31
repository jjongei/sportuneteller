package com.sportuenteller.olympic.games.vote.domain.team;




import com.sportuenteller.olympic.common.code.MedalType;
import com.sportuenteller.olympic.common.model.Countries;
import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.games.game.domain.GameId;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="team")
public class Team {

    @EmbeddedId
    private TeamId id;

    @Embedded
    private GameId gameId;

    @Embedded
    private Countries countries;

    @Column(name = "medal_type", columnDefinition = "nvarchar(32)")
    @Enumerated(EnumType.STRING)
    private MedalType medalType;

    @Column(name = "vote", columnDefinition = "bigint", nullable = false)
    private Long vote;

    @Column(name="voting_available", columnDefinition="char(1)", nullable=false)
    private Boolean votingAvailable;

    @ElementCollection
    @CollectionTable(name = "team_player", joinColumns = @JoinColumn(name = "team_id"))
    @OrderColumn(name = "line_idx")
    private List<TeamPlayer> teamPlayers;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Team(){}


    public Team(TeamId id, GameId gameId, Countries countries, List<TeamPlayer> teamPlayers) {
        this.setId(id);
        this.setGameId(gameId);
        this.setCountries(countries);
        this.setMedalType(MedalType.none);
        this.setVote(0L);
        this.setVotingAvailable(true);
        this.setTeamPlayers(teamPlayers);

        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }

    /**
     * Team player를 변경한다.
     * @param teamPlayers
     */
    public void changeTeamPlayers(List<TeamPlayer> teamPlayers){
       this.setTeamPlayers(teamPlayers);
    }

    public void changeMedal(MedalType medalType){
        this.medalType = medalType == null ? MedalType.none : medalType;
    }

    public void changeVote(long vote){
        this.vote+= vote;
    }

    public boolean isGold(){
        return medalType != null && medalType.equals(MedalType.gold);
    }
    public boolean isSiver(){
        return medalType != null && medalType.equals(MedalType.silver);
    }
    public boolean isBronze(){
        return medalType != null && medalType.equals(MedalType.bronze);
    }

    private void verifyAtLeastOnOrMoreTeamPlayers(List<TeamPlayer> teamPlayers){
        if(teamPlayers == null || teamPlayers.isEmpty())
            throw new IllegalArgumentException("Team player");
    }

    private void setId(TeamId id) {
        if(id == null) throw new IllegalArgumentException("TeamId");
        this.id = id;
    }

    private void setGameId(GameId gameId) {
        if(gameId == null) throw new IllegalArgumentException("GameId");
        this.gameId = gameId;
    }

    private void setCountries(Countries countries) {
        if(countries == null) throw new IllegalArgumentException("Countries");
        this.countries = countries;
    }

    private void setMedalType(MedalType medalType) {
        if(medalType == null) throw new IllegalArgumentException("MedalType");
        this.medalType = medalType;
    }

    private void setVote(Long vote) {
        this.vote = vote != null ? vote : 0L;
    }
    private void setVotingAvailable(Boolean votingAvailable) {
        this.votingAvailable = votingAvailable !=null ? votingAvailable : true;
    }
    private void setTeamPlayers(List<TeamPlayer> teamPlayers){
        this.verifyAtLeastOnOrMoreTeamPlayers(teamPlayers);
        this.teamPlayers = teamPlayers;
    }

}

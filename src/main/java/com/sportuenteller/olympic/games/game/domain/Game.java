package com.sportuenteller.olympic.games.game.domain;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.code.StatusType;
import com.sportuenteller.olympic.common.model.Name;
import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="game")
public class Game {

    @EmbeddedId
    private GameId id;

    @Column(name = "game_type", columnDefinition = "char(4)", nullable = false)
    private GameType gameType;

    @Embedded
    private Name name;

    @Embedded
    private Schedule schedule;

    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "vote_start_date"))
            , @AttributeOverride(name = "endDate", column = @Column(name = "vote_end_date"))
    })
    @Embedded
    private Schedule voteSchedule;

    @Column(name = "status_type", columnDefinition = "int", nullable = false)
    private StatusType statusType;

    @Column(name="groups", columnDefinition="char(1)", nullable=false)
    private Boolean group;

    @Column(name="termiation", columnDefinition="char(1)", nullable=false)
    private Boolean termination;

    @Column(name="hot_game", columnDefinition="char(1)", nullable=false)
    private Boolean hotgame;

    @Column(name="reward", columnDefinition="char(1)", nullable=false)
    private Boolean reward;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Game(){}

    public Game(GameId id, GameType gameType, Name name, Schedule schedule, Boolean group) {
        this(id, gameType, name, schedule, null, group);
    }

    public Game(GameId id, GameType gameType, Name name, Schedule schedule, Schedule voteSchedule, Boolean group) {
        this.setId(id);
        this.setGameType(gameType);
        this.setName(name);
        this.setGroup(group);
        this.setTermination(false);
        this.setGameAndVoteSchedule(schedule, voteSchedule);

        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }
    private void setGameAndVoteSchedule(Schedule schedule, Schedule voteSchedule){
        this.setSchedule(schedule);
        this.setVoteSchedule(voteSchedule != null ? voteSchedule : schedule.createTempSchedule());

        this.calculatorStatusType(this.voteSchedule);
    }
    public void terminate(){
        this.setTermination(true);
        this.statusType = StatusType.termination;
    }
    public void cancelTerminate(){
        this.setTermination(false);
        this.calculatorStatusType(this.voteSchedule);
    }
    public void changeStatus(){
        if(this.statusType.equals(StatusType.wait)){
            this.statusType = StatusType.possible;
        }else if(this.statusType.equals(StatusType.possible)){
            this.statusType = StatusType.termination;
        }
    }

    public void modifyGame(GameType gameType, Name name, Schedule schedule, Schedule voteSchedule, boolean group){
        this.gameType = gameType;
        this.name = name;
        this.group = group;
        this.schedule = schedule;
        this.setGameAndVoteSchedule(schedule, voteSchedule);
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }


    /**
     * Schedule를 이용해서 경기 투표 상태를 계산한다.
     */
    private void calculatorStatusType(Schedule voteSchedule){
        if(voteSchedule == null || (this.termination!= null && this.termination)){
            return;
        }

        StatusType newStatusType = voteSchedule.calculatorStatus();

        if(!newStatusType.equals(this.statusType)){
            this.statusType = newStatusType;
        }
    }

    private void setId(GameId id) {
        if(id == null) throw new IllegalArgumentException("Game ID");
        this.id = id;
    }

    private void setGameType(GameType gameType) {
        if(gameType == null) throw new IllegalArgumentException("Game Type");
        this.gameType = gameType;
    }

    private void setName(Name name) {
        if(name == null) throw new IllegalArgumentException("Game Name");
        this.name = name;
    }

    private void setSchedule(Schedule schedule) {
        if(schedule == null) throw new IllegalArgumentException("Game Schedule");
        this.schedule = schedule;
    }

    private void setVoteSchedule(Schedule voteSchedule) {
        if(voteSchedule == null) throw new IllegalArgumentException("Vote Schedule");
        this.voteSchedule = voteSchedule;
    }
    private void setStatusType(StatusType statusType) {
        if(statusType == null) throw new IllegalArgumentException("StatusType");
        this.statusType = statusType;
    }
    private void setGroup(Boolean group) {
        this.group = group != null? group : false;
    }


    private void setTermination(Boolean termination) {
        this.termination = termination != null ? termination : false;
    }
}

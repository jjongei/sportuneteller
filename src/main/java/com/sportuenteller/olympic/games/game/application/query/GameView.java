package com.sportuenteller.olympic.games.game.application.query;

import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.games.game.domain.Game;
import lombok.Getter;

import java.util.Date;

@Getter
public class GameView {
    private long gameId;
    private String gameTypeName;
    private String gameTypeCode;
    private String gameTypeSubject;
    private String gameNameKr;
    private String gameNameEn;
    private Date startDate;
    private Date endDate;
    private Date voteStartDate;
    private Date voteEndDate;
    private String statusTypeName;
    private String statusTypeSubject;
    private boolean group;
    private boolean termination;

    public GameView(Game game){
        this.gameId = game.getId().getValue();
        this.gameTypeName = game.getGameType().name();
        this.gameTypeCode = game.getGameType().getCode();
        this.gameTypeSubject = game.getGameType().getSubject();
        this.gameNameEn = game.getName().getNameEn();
        this.gameNameKr = game.getName().getNameKr();
        this.startDate = game.getSchedule().getStartDate();
        this.endDate = game.getSchedule().getEndDate();
        this.voteStartDate = game.getVoteSchedule().getStartDate();
        this.voteEndDate = game.getVoteSchedule().getEndDate();
        this.statusTypeName = game.getStatusType().name();
        this.statusTypeSubject = game.getStatusType().getSubject();

        this.group = game.getGroup() != null ? game.getGroup() : false;
        this.termination = game.getTermination() !=null ? game.getTermination() : false;
    }

    public String getConvertStartDate(){
        return DateUtil.format(this.startDate);
    }
    public String getConvertEndDate(){

        return DateUtil.format(this.endDate);
    }

    public String getConvertVoteStartDate(){
        return DateUtil.format(this.voteStartDate);
    }
    public String getConvertVoteEndDate(){
        return DateUtil.format(this.voteEndDate);
    }
}

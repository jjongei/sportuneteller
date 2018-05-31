package com.sportuenteller.olympic.common.excel.game;

import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WriteGameExcelRequest {

    private long gameId;
    private String gameTypeName;
    private String gameTypeCode;
    private String gameTypeSubject;
    private String gameNameKr;
    private String gameNameEn;
    private String startDate;
    private String endDate;
    private String voteStartDate;
    private String voteEndDate;
    private String statusTypeName;
    private String statusTypeSubject;
    private String group;
    private String termination;

    public WriteGameExcelRequest(long gameId
            , String gameTypeName
            , String gameTypeCode
            , String gameTypeSubject
            , String gameNameKr
            , String gameNameEn
            , Date startDate
            , Date endDate
            , Date voteStartDate
            , Date voteEndDate
            , String statusTypeName
            , String statusTypeSubject
            , Boolean group
            , Boolean termination) {


        this.gameId = gameId;
        this.gameTypeName = gameTypeName;
        this.gameTypeCode = gameTypeCode;
        this.gameTypeSubject = gameTypeSubject;
        this.gameNameKr = gameNameKr;
        this.gameNameEn = gameNameEn;

        this.startDate = DateUtil.format(startDate);
        this.endDate = DateUtil.format(endDate);
        this.voteStartDate = DateUtil.format(voteStartDate);
        this.voteEndDate = DateUtil.format(voteEndDate);
        this.statusTypeName = statusTypeName;
        this.statusTypeSubject = statusTypeSubject;

        this.group = group != null && group ? "T" : "F";
        this.termination = termination != null && termination ? "T" : "F";
    }
}

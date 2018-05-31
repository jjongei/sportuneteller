package com.sportuenteller.olympic.games.vote.application.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TeamVoteStatus {
    private long rownum;
    private String sessionKey;
    private long teamId;
    private String name;
    private String userId;
    private long point;
    private Date voteDate;
    private boolean myrank;

    public TeamVoteStatus(Object rownum, Object sessionKey, Object teamId, Object name, Object userId, Object point, Object voteDate, Object myrank) {
        this.rownum = rownum != null ? (Long) rownum : 0L;
        this.sessionKey = sessionKey != null ? (String)sessionKey: null;
        this.teamId = teamId != null ? (Long) teamId: 0L ;
        this.name = name != null ? (String)name: null;
        this.userId = userId != null ? (String)userId: null;
        this.point = point != null ? (Long) point : 0L ;
        this.voteDate = voteDate != null ? (Date) voteDate: null ;
        this.myrank = myrank != null ? "T".equals((String) myrank) : false ;
    }
}

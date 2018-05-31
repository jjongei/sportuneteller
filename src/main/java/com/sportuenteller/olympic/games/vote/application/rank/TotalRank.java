package com.sportuenteller.olympic.games.vote.application.rank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TotalRank {

    private long rownum;
    private String sessionKey;
    private String name;
    private String userId;
    private long point;
    private long totalCount;
    private long goldCount;
    private long silverCount;
    private long bronzeCount;
    private boolean myrank;

    public TotalRank(Object rownum, Object sessionKey, Object userId, Object name
            , Object goldCount, Object silverCount, Object bronzeCount, Object totalCount, Object point, Object myrank) {
        this.rownum = rownum != null ? (Long) rownum : 0L;
        this.sessionKey = sessionKey !=null ? (String) sessionKey : null;
        this.name = name !=null ? (String) name : null;
        this.userId = userId !=null ? (String) userId : null;
        this.point = point != null ? (Long) point : 0L;;
        this.totalCount = totalCount != null ? (Long) totalCount : 0L;;
        this.goldCount = goldCount != null ? (Long) goldCount : 0L;;
        this.silverCount = silverCount != null ? (Long) silverCount : 0L;;
        this.bronzeCount = bronzeCount != null ? (Long) bronzeCount : 0L;;
        this.myrank = myrank != null ? "T".equals(myrank) : false;
    }
}

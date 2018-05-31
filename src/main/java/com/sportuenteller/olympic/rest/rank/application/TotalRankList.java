package com.sportuenteller.olympic.rest.rank.application;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TotalRankList {
    private Long rank;
    private String userId;
    private String name;
    private String sessionKey;
    private boolean isRanking;
    private Long point =0L;
    private Long goldCount = 0L;
    private Long silverCount= 0L;
    private Long bronzeCount = 0L;
    private Long medalTotalCount = 0L;

    public TotalRankList(Long rank, String userId, String name, String sessionKey, boolean isRanking, Long point) {
        this.rank = rank;
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
        this.isRanking = isRanking;
        this.point = point;
    }

    public TotalRankList(Long rank, String userId, String name, String sessionKey, boolean isRanking, Long goldCount, Long silverCount, Long bronzeCount, Long medalTotalCount) {
        this.rank = rank;
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
        this.isRanking = isRanking;
        this.goldCount = goldCount;
        this.silverCount = silverCount;
        this.bronzeCount = bronzeCount;
        this.medalTotalCount = medalTotalCount;
    }
}

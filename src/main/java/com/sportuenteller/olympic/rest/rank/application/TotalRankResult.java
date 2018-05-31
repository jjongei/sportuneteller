package com.sportuenteller.olympic.rest.rank.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TotalRankResult {
    private String userId;
    private String sessionKey;

    private List<TotalRankList> points = new ArrayList<>();
    private List<TotalRankList> medals = new ArrayList<>();

    public TotalRankResult(String userId, String sessionKey, List<TotalRankList> points, List<TotalRankList> medals) {
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.points = points;
        this.medals = medals;
    }
}

package com.sportuenteller.olympic.games.vote.application.rank;


import java.util.List;

public interface TotalRankDao {
    public static final String pointOrderBy ="ORDER BY point desc";
    public static final String medalOrderBy ="ORDER BY gold_count desc, silver_count desc, bronze_count desc";
    public List<TotalRank> selectList(String sessionKey, String orderBy);
}

package com.sportuenteller.olympic.games.vote.application.dao;

import java.util.List;

public interface TeamSummaryDao {
    List<TeamSummary> selectList(long gameId);
}

package com.sportuenteller.olympic.games.vote.application.dao;

import java.util.List;

public interface TeamVoteStatusDao {

    public static final String pointOrderBy ="ORDER BY b.point desc, a.vote_date desc, b.user_id asc";
    public static final String voteDateOrderBy ="ORDER BY a.vote_date desc, b.user_id asc";

    List<TeamVoteStatus> selectList(long teamId, String sessionKey, String orderBy);
}

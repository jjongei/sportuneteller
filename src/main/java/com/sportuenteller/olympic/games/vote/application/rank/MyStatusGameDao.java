package com.sportuenteller.olympic.games.vote.application.rank;

import java.util.List;

public interface MyStatusGameDao {
    public List<MyStatusGame> selectList(List<Long> idList);
}

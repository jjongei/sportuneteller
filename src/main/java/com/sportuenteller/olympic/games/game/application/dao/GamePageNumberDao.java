package com.sportuenteller.olympic.games.game.application.dao;

public interface GamePageNumberDao {

    public int selectGamePageNumber(String gameTypeCode, long gameId);
}

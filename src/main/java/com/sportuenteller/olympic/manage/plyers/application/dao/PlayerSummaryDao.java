package com.sportuenteller.olympic.manage.plyers.application.dao;

import java.util.List;

public interface PlayerSummaryDao {

    List<PlayerSummary> selectList(PlayerSummarySearchRequest request);

}

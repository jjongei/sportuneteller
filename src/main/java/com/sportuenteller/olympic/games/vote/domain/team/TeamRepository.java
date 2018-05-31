package com.sportuenteller.olympic.games.vote.domain.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TeamRepository {
    Team findById(TeamId id);
    List<Team> findList(TeamSearchRequest request);
    List<Team> findList(TeamSearchRequest request, Sort sort);

    Page<Team> findList(TeamSearchRequest request, Pageable pageable);

    long max();

    void save(Team team);
    void remove(Team team);
}

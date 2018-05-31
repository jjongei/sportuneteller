package com.sportuenteller.olympic.manage.plyers.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PlayerRepository {
    Player findById(PlayerId id);
    List<Player> findList(PlayerSearchRequest request);
    List<Player> findList(PlayerSearchRequest request, Sort sort);

    Page<Player> findList(PlayerSearchRequest request, Pageable pageable);

    long max();

    void save(Player player);
    void remove(Player player);

}

package com.sportuenteller.olympic.manage.plyers.infra.repository;

import com.sportuenteller.olympic.common.query.Order;
import com.sportuenteller.olympic.common.query.QueryDslBuilder;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.manage.plyers.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPlayerRepository extends QueryDslBuilder<Player> implements PlayerRepository {

    public JpaPlayerRepository() {
        super(Player.class);
    }

    @Override
    protected Sort getSort() {
       return SortBuilder.build(new Order[]{new Order(false, "gameType")
                    , new Order(false, "countryId.value")
                    , new Order(true, "sexuality")});
    }
    @Override
    public Player findById(PlayerId id) {
        QPlayer qPlayer = QPlayer.player;
        return this.from(qPlayer).where(qPlayer.id.eq(id)).fetchOne();
    }

    @Override
    public List<Player> findList(PlayerSearchRequest request) {
        return this.findList(request, this.getSort());
    }

    @Override
    public List<Player> findList(PlayerSearchRequest request, Sort sort) {
        return super.findList(QPlayer.player, request, sort);
    }

    @Override
    public Page<Player> findList(PlayerSearchRequest request, Pageable pageable) {
        return super.findList(QPlayer.player, request, pageable);
    }

    @Override
    public long max() {
        QPlayer qPlayer = QPlayer.player;
        Long max = this.from(qPlayer).select(qPlayer.id.value.max()).fetchOne();
        return max != null ? max+1 : 1;
    }

    @Override
    public void save(Player player) {
        this.getEntityManager().persist(player);
    }

    @Override
    public void remove(Player player) {
        this.getEntityManager().remove(player);
    }
}

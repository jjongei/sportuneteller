package com.sportuenteller.olympic.games.game.infra.repository;

import com.sportuenteller.olympic.common.query.Order;
import com.sportuenteller.olympic.common.query.QueryDslBuilder;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.games.game.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaGameRepository extends QueryDslBuilder<Game> implements GameRepository {

    public JpaGameRepository() {
        super(Game.class);
    }

    @Override
    protected Sort getSort() {
        return SortBuilder.build(new Order[]{new Order(false, "gameType")
                , new Order(false, "statusType")
                , new Order(false, "voteSchedule.startDate")
                , new Order(false, "id.value")});
    }
    @Override
    public Game findById(GameId id) {
        QGame qGame = QGame.game;

        return this.from(qGame).where(qGame.id.eq(id)).fetchOne();
    }

    @Override
    public List<Game> findList(GameSearchRequest request) {
        return this.findList(request, this.getSort());
    }

    @Override
    public List<Game> findList(GameSearchRequest request, Sort sort) {
        return super.findList(QGame.game, request, sort);
    }

    @Override
    public Page<Game> findList(GameSearchRequest request, Pageable pageable) {
        return super.findList(QGame.game, request, pageable);
    }

    @Override
    public long max() {
        QGame qGame = QGame.game;
        Long max = this.from(qGame).select(qGame.id.value.max()).fetchOne();
        return max != null ? max+1 : 1;
    }

    @Override
    public void save(Game game) {
        this.getEntityManager().persist(game);
    }

    @Override
    public void remove(Game game) {
        this.getEntityManager().remove(game);
    }
}

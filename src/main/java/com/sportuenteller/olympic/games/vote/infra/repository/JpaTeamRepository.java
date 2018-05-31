package com.sportuenteller.olympic.games.vote.infra.repository;

import com.sportuenteller.olympic.common.query.Order;
import com.sportuenteller.olympic.common.query.QueryDslBuilder;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.games.vote.domain.team.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTeamRepository extends QueryDslBuilder<Team> implements TeamRepository {

    public JpaTeamRepository() {
        super(Team.class);
    }

    @Override
    protected Sort getSort() {
        return SortBuilder.build(new Order[]{new Order(false, "gameId")
                , new Order(true, "vote")
                , new Order(false, "id.value")});
    }
    @Override
    public Team findById(TeamId id) {
        QTeam qTeam = QTeam.team;
        return this.from(qTeam).where(qTeam.id.eq(id)).fetchOne();
    }

    @Override
    public List<Team> findList(TeamSearchRequest request) {
        return this.findList(request, this.getSort());
    }

    @Override
    public List<Team> findList(TeamSearchRequest request, Sort sort) {
        return super.findList(QTeam.team, request, sort);
    }

    @Override
    public Page<Team> findList(TeamSearchRequest request, Pageable pageable) {
        return super.findList(QTeam.team, request, pageable);
    }

    @Override
    public long max() {
        QTeam qTeam = QTeam.team;
        Long max = this.from(qTeam).select(qTeam.id.value.max()).fetchOne();
        return max != null ? max+1 : 1;
    }

    @Override
    public void save(Team team) {
        this.getEntityManager().persist(team);
    }

    @Override
    public void remove(Team team) {
        this.getEntityManager().remove(team);
    }
}

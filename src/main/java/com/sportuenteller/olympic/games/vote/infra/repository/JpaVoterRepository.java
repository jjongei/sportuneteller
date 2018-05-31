package com.sportuenteller.olympic.games.vote.infra.repository;

import com.sportuenteller.olympic.common.query.Order;
import com.sportuenteller.olympic.common.query.QueryDslBuilder;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.games.vote.domain.voter.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaVoterRepository extends QueryDslBuilder<Voter> implements VoterRepository {

    public JpaVoterRepository() {
        super(Voter.class);
    }

    @Override
    protected Sort getSort() {
        return SortBuilder.build(new Order[]{new Order(true, "point")
                , new Order(false, "createDate")});
    }
    @Override
    public Voter findById(VoterId id) {
        QVoter qVoter = QVoter.voter;
        return this.from(qVoter).where(qVoter.id.eq(id)).fetchOne();
    }

    @Override
    public List<Voter> findList(VoterSearchRequest request) {
        return this.findList(request, this.getSort());
    }

    @Override
    public List<Voter> findList(VoterSearchRequest request, Sort sort) {
        return super.findList(QVoter.voter, request, sort);
    }

    @Override
    public Page<Voter> findList(VoterSearchRequest request, Pageable pageable) {
        return super.findList(QVoter.voter, request, pageable);
    }

    @Override
    public void save(Voter voter) {
        this.getEntityManager().persist(voter);
    }

    @Override
    public void remove(Voter voter) {
        this.getEntityManager().remove(voter);
    }
}

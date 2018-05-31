package com.sportuenteller.olympic.games.vote.infra.dao;

import com.sportuenteller.olympic.games.game.application.dao.VotePerGameCount;
import com.sportuenteller.olympic.games.vote.application.dao.TeamVoteStatus;
import com.sportuenteller.olympic.games.vote.application.dao.TeamVoteStatusDao;
import org.hibernate.SQLQuery;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryTeamVoteStatusDao implements TeamVoteStatusDao{
    @PersistenceContext
    EntityManager em;

    @Override
    public List<TeamVoteStatus> selectList(long teamId, String sessionKey, String orderBy) {

        List<TeamVoteStatus> list = new ArrayList<>();

        String sql ="select  @rownum \\:= @rownum+1 rownum, a.* from(\n" +
                "  select a.* from(\n" +
                "  select\n" +
                "    b.session_key, a.team_id,\n" +
                "    b.name, b.user_id, b.point, a.vote_date,  CASE WHEN b.session_key = :sessionKey THEN 'T' ELSE 'F' END myrank\n" +
                "from vote_team a INNER join voter b ON a.session_key = b.session_key where a.team_id = :teamId "+orderBy+" limit 20 ) a\n" +
                "UNION\n" +
                "select\n" +
                "    b.session_key, a.team_id,\n" +
                "    b.name, b.user_id, b.point, a.vote_date, 'T' as myrank\n" +
                "from vote_team a INNER join voter b ON a.session_key = b.session_key\n" +
                "WHERE a.session_key = :sessionKey and a.team_id = :teamId\n" +
                ")a, (select @rownum \\:= 0) R\n";

        Query query = em.createNativeQuery(sql)
                .setParameter("sessionKey", sessionKey)
                .setParameter("teamId",teamId);

        query.unwrap(SQLQuery.class).addScalar("rownum", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("session_key", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("team_id", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("name", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("user_id", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("point", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("vote_date", DateType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("myrank", StringType.INSTANCE);

        List<Object[]> resultList = query.getResultList();

        for(Object[] row : resultList){
            list.add(new TeamVoteStatus(row[0], row[1]
                    , row[2], row[3], row[4], row[5] , row[6], row[7]));
        }
        return list;
    }
}

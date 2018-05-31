package com.sportuenteller.olympic.games.vote.infra.dao;

import com.sportuenteller.olympic.games.vote.application.dao.TeamVoteStatus;
import com.sportuenteller.olympic.games.vote.application.rank.TotalRank;
import com.sportuenteller.olympic.games.vote.application.rank.TotalRankDao;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryTotalRankDao implements TotalRankDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<TotalRank> selectList(String sessionKey, String orderBy) {
        List<TotalRank> list = new ArrayList<>();

        String sql ="select  @rownum \\:= @rownum+1 rownum, a.* from(\n" +
                "  select a.* from(\n" +
                "    select\n" +
                "      session_key, user_id, name, gold_count, silver_count, bronze_count, (gold_count+voter.silver_count+voter.bronze_count) as total_count , point\n" +
                "    , CASE WHEN session_key = :sessionKey THEN 'T' ELSE 'F' END myrank\n" +
                "    from voter "+orderBy+" limit 20\n" +
                "  )a\n" +
                "  UNION\n" +
                "  select\n" +
                "      session_key, user_id, name, gold_count, silver_count, bronze_count, (gold_count+voter.silver_count+voter.bronze_count) as total_count , point\n" +
                "    ,  'T' as myrank\n" +
                "  from voter where session_key =:sessionKey\n" +
                ")a, (select @rownum \\:= 0) R";
        Query query = em.createNativeQuery(sql)
                .setParameter("sessionKey", sessionKey);

        query.unwrap(SQLQuery.class).addScalar("rownum", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("session_key", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("user_id", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("name", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("gold_count", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("silver_count", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("bronze_count", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("total_count", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("point", LongType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("myrank", StringType.INSTANCE);

        List<Object[]> resultList = query.getResultList();
        for(Object[] row : resultList){
            list.add(new TotalRank(row[0], row[1]
                    , row[2], row[3], row[4], row[5] , row[6], row[7], row[8], row[9]));
        }
        return list;
    }
}

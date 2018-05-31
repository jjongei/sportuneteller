package com.sportuenteller.olympic.games.game.infra.dao;


import com.sportuenteller.olympic.games.game.application.dao.GamePageNumberDao;
import com.sportuenteller.olympic.games.game.application.dao.VotePerGameCount;
import com.sportuenteller.olympic.games.game.application.dao.VotePerGameCountDao;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryGameSummaryDao implements VotePerGameCountDao, GamePageNumberDao {
    @PersistenceContext
    EntityManager em;

    public List<VotePerGameCount> selectVotePerGameCount(){
        List<VotePerGameCount> result = new ArrayList<>();

        String sql ="select a.game_type as game_type, a.hot_game, a.reward, ifnull(d.cnt,0) vote_cnt, count(c.session_key) count from game a\n" +
                "                             left OUTER JOIN team b on a.game_id = b.game_id\n" +
                "                             left outer join vote_team c on b.team_id = c.team_id\n" +
                "                             left outer join (select game_type, count(1) cnt from game where status_type =1 GROUP BY  game_type) d on a.game_type = d.game_type\n" +
                "                             group by a.game_type, a.hot_game, a.reward, d.cnt";

        Query query = em.createNativeQuery(sql);

        query.unwrap(SQLQuery.class).addScalar("game_type", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("hot_game", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("reward", StringType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("vote_cnt", IntegerType.INSTANCE);
        query.unwrap(SQLQuery.class).addScalar("count", IntegerType.INSTANCE);

        List<Object[]> resultList = query.getResultList();

        for(Object[] row : resultList){
            result.add(new VotePerGameCount(row[0], row[1], row[2],row[3],row[4]));
        }
        return result;
    }


    @Override
    public int selectGamePageNumber(String gameTypeCode, long gameId) {
        int pageNumber = 0;
        String sql = "select\n" +
                "    rownum\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    @ROWNUM \\:= @ROWNUM + 1 rownum,\n" +
                "    a.game_id,\n" +
                "    a.name_en,\n" +
                "    a.status_type,\n" +
                "    a.vote_start_date\n" +
                "  FROM game a, (SELECT @ROWNUM \\:= 0) R\n" +
                "  WHERE a.game_type='"+gameTypeCode+"'\n" +
                "  ORDER BY a.game_type ASC, a.status_type ASC, a.vote_start_date ASC, a.game_id ASC\n" +
                ")a where game_id="+gameId;

        Query query = em.createNativeQuery(sql);

        query.unwrap(SQLQuery.class).addScalar("rownum", IntegerType.INSTANCE);
        Object obj = query.getSingleResult();
        if(obj != null){
            pageNumber = (Integer) obj;
        }
        return pageNumber;
    }
}

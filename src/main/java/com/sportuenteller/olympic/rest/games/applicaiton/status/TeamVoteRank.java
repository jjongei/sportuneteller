package com.sportuenteller.olympic.rest.games.applicaiton.status;

import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TeamVoteRank implements Serializable {
    private String name;
    private String userId;
    private String sessionKey;
    private long point;
    private String voteDate;
    private boolean myrank;
    private long rank;

    public TeamVoteRank(String name, String userId, String sessionKey, long point, boolean myrank, long rank) {
        this.name = name;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.point = point;
        this.myrank = myrank;
        this.rank = rank;
    }

    public TeamVoteRank(String name, String userId, String sessionKey,Date voteDate, boolean myrank, long rank) {
        this.name = name;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.voteDate = DateUtil.format(voteDate);
        this.myrank = myrank;
        this.rank = rank;
    }
}

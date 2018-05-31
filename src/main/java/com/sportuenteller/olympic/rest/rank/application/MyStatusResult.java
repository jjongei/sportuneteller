package com.sportuenteller.olympic.rest.rank.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class MyStatusResult implements Serializable{

    private String name;
    private String userId;
    private String sessionKey;
    private Long point = 0L;
    private Long voteCount = 0L;
    private Long goldCount = 0L;
    private Long silverCount= 0L;
    private Long bronzeCount = 0L;
    private List<MyStatusGameResult> games;

    public MyStatusResult(String name, String userId, String sessionKey) {
        this.name = name;
        this.userId = userId;
        this.sessionKey = sessionKey;
    }


    public MyStatusResult(String name, String userId, String sessionKey, Long point, Long voteCount, Long goldCount, Long silverCount, Long bronzeCount, List<MyStatusGameResult> games) {
        this.name = name;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.point = point;
        this.voteCount = voteCount;
        this.goldCount = goldCount;
        this.silverCount = silverCount;
        this.bronzeCount = bronzeCount;
        this.games = games;
    }
}

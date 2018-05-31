package com.sportuenteller.olympic.rest.games.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotFoundGameException extends RuntimeException {
    private long gameid;
    public NotFoundGameException(long gameId) {
        super();
        this.gameid = gameId;
    }
}

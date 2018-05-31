package com.sportuenteller.olympic.games.vote.domain.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamPlayer is a Querydsl query type for TeamPlayer
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTeamPlayer extends BeanPath<TeamPlayer> {

    private static final long serialVersionUID = 1319865358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamPlayer teamPlayer = new QTeamPlayer("teamPlayer");

    public final com.sportuenteller.olympic.common.model.QPlayers player;

    public QTeamPlayer(String variable) {
        this(TeamPlayer.class, forVariable(variable), INITS);
    }

    public QTeamPlayer(Path<? extends TeamPlayer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamPlayer(PathMetadata metadata, PathInits inits) {
        this(TeamPlayer.class, metadata, inits);
    }

    public QTeamPlayer(Class<? extends TeamPlayer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.player = inits.isInitialized("player") ? new com.sportuenteller.olympic.common.model.QPlayers(forProperty("player")) : null;
    }

}


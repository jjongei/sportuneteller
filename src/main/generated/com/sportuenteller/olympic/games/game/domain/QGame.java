package com.sportuenteller.olympic.games.game.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGame extends EntityPathBase<Game> {

    private static final long serialVersionUID = 43653723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGame game = new QGame("game");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final EnumPath<com.sportuenteller.olympic.common.code.GameType> gameType = createEnum("gameType", com.sportuenteller.olympic.common.code.GameType.class);

    public final BooleanPath group = createBoolean("group");

    public final BooleanPath hotgame = createBoolean("hotgame");

    public final QGameId id;

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final com.sportuenteller.olympic.common.model.QName name;

    public final BooleanPath reward = createBoolean("reward");

    public final QSchedule schedule;

    public final EnumPath<com.sportuenteller.olympic.common.code.StatusType> statusType = createEnum("statusType", com.sportuenteller.olympic.common.code.StatusType.class);

    public final BooleanPath termination = createBoolean("termination");

    public final QSchedule voteSchedule;

    public QGame(String variable) {
        this(Game.class, forVariable(variable), INITS);
    }

    public QGame(Path<? extends Game> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGame(PathMetadata metadata, PathInits inits) {
        this(Game.class, metadata, inits);
    }

    public QGame(Class<? extends Game> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QGameId(forProperty("id")) : null;
        this.name = inits.isInitialized("name") ? new com.sportuenteller.olympic.common.model.QName(forProperty("name")) : null;
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule")) : null;
        this.voteSchedule = inits.isInitialized("voteSchedule") ? new QSchedule(forProperty("voteSchedule")) : null;
    }

}


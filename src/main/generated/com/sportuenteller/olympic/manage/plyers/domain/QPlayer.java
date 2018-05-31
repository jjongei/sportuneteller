package com.sportuenteller.olympic.manage.plyers.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -1837572511L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final com.sportuenteller.olympic.manage.countries.domain.QCountryId countryId;

    public final StringPath countryReference = createString("countryReference");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final EnumPath<com.sportuenteller.olympic.common.code.GameType> gameType = createEnum("gameType", com.sportuenteller.olympic.common.code.GameType.class);

    public final QPlayerId id;

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final ListPath<ParticipationGame, QParticipationGame> participationGames = this.<ParticipationGame, QParticipationGame>createList("participationGames", ParticipationGame.class, QParticipationGame.class, PathInits.DIRECT2);

    public final BooleanPath sexuality = createBoolean("sexuality");

    public QPlayer(String variable) {
        this(Player.class, forVariable(variable), INITS);
    }

    public QPlayer(Path<? extends Player> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayer(PathMetadata metadata, PathInits inits) {
        this(Player.class, metadata, inits);
    }

    public QPlayer(Class<? extends Player> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.countryId = inits.isInitialized("countryId") ? new com.sportuenteller.olympic.manage.countries.domain.QCountryId(forProperty("countryId")) : null;
        this.id = inits.isInitialized("id") ? new QPlayerId(forProperty("id")) : null;
    }

}


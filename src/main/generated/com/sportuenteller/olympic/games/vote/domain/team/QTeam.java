package com.sportuenteller.olympic.games.vote.domain.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -746581299L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeam team = new QTeam("team");

    public final com.sportuenteller.olympic.common.model.QCountries countries;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final com.sportuenteller.olympic.games.game.domain.QGameId gameId;

    public final QTeamId id;

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final EnumPath<com.sportuenteller.olympic.common.code.MedalType> medalType = createEnum("medalType", com.sportuenteller.olympic.common.code.MedalType.class);

    public final ListPath<TeamPlayer, QTeamPlayer> teamPlayers = this.<TeamPlayer, QTeamPlayer>createList("teamPlayers", TeamPlayer.class, QTeamPlayer.class, PathInits.DIRECT2);

    public final NumberPath<Long> vote = createNumber("vote", Long.class);

    public final BooleanPath votingAvailable = createBoolean("votingAvailable");

    public QTeam(String variable) {
        this(Team.class, forVariable(variable), INITS);
    }

    public QTeam(Path<? extends Team> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeam(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeam(PathMetadata metadata, PathInits inits) {
        this(Team.class, metadata, inits);
    }

    public QTeam(Class<? extends Team> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.countries = inits.isInitialized("countries") ? new com.sportuenteller.olympic.common.model.QCountries(forProperty("countries")) : null;
        this.gameId = inits.isInitialized("gameId") ? new com.sportuenteller.olympic.games.game.domain.QGameId(forProperty("gameId")) : null;
        this.id = inits.isInitialized("id") ? new QTeamId(forProperty("id")) : null;
    }

}


package com.sportuenteller.olympic.games.vote.domain.voter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteTeam is a Querydsl query type for VoteTeam
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QVoteTeam extends BeanPath<VoteTeam> {

    private static final long serialVersionUID = 853033198L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteTeam voteTeam = new QVoteTeam("voteTeam");

    public final com.sportuenteller.olympic.games.game.domain.QGameId gameId;

    public final com.sportuenteller.olympic.games.vote.domain.team.QTeamId teamId;

    public final DateTimePath<java.util.Date> voteDate = createDateTime("voteDate", java.util.Date.class);

    public QVoteTeam(String variable) {
        this(VoteTeam.class, forVariable(variable), INITS);
    }

    public QVoteTeam(Path<? extends VoteTeam> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteTeam(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteTeam(PathMetadata metadata, PathInits inits) {
        this(VoteTeam.class, metadata, inits);
    }

    public QVoteTeam(Class<? extends VoteTeam> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gameId = inits.isInitialized("gameId") ? new com.sportuenteller.olympic.games.game.domain.QGameId(forProperty("gameId")) : null;
        this.teamId = inits.isInitialized("teamId") ? new com.sportuenteller.olympic.games.vote.domain.team.QTeamId(forProperty("teamId")) : null;
    }

}


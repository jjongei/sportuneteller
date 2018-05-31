package com.sportuenteller.olympic.games.vote.domain.voter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoter is a Querydsl query type for Voter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVoter extends EntityPathBase<Voter> {

    private static final long serialVersionUID = 905704353L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoter voter = new QVoter("voter");

    public final NumberPath<Long> bronzeCount = createNumber("bronzeCount", Long.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> goldCount = createNumber("goldCount", Long.class);

    public final QVoterId id;

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final NumberPath<Long> slverCount = createNumber("slverCount", Long.class);

    public final StringPath userId = createString("userId");

    public final ListPath<VoteTeam, QVoteTeam> voteTeams = this.<VoteTeam, QVoteTeam>createList("voteTeams", VoteTeam.class, QVoteTeam.class, PathInits.DIRECT2);

    public QVoter(String variable) {
        this(Voter.class, forVariable(variable), INITS);
    }

    public QVoter(Path<? extends Voter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoter(PathMetadata metadata, PathInits inits) {
        this(Voter.class, metadata, inits);
    }

    public QVoter(Class<? extends Voter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QVoterId(forProperty("id")) : null;
    }

}


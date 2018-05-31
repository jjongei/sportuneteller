package com.sportuenteller.olympic.games.vote.domain.voter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVoterId is a Querydsl query type for VoterId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QVoterId extends BeanPath<VoterId> {

    private static final long serialVersionUID = -1496475492L;

    public static final QVoterId voterId = new QVoterId("voterId");

    public final StringPath value = createString("value");

    public QVoterId(String variable) {
        super(VoterId.class, forVariable(variable));
    }

    public QVoterId(Path<? extends VoterId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVoterId(PathMetadata metadata) {
        super(VoterId.class, metadata);
    }

}


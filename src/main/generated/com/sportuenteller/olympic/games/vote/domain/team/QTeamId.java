package com.sportuenteller.olympic.games.vote.domain.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamId is a Querydsl query type for TeamId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTeamId extends BeanPath<TeamId> {

    private static final long serialVersionUID = -205087544L;

    public static final QTeamId teamId = new QTeamId("teamId");

    public final NumberPath<Long> value = createNumber("value", Long.class);

    public QTeamId(String variable) {
        super(TeamId.class, forVariable(variable));
    }

    public QTeamId(Path<? extends TeamId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamId(PathMetadata metadata) {
        super(TeamId.class, metadata);
    }

}


package com.sportuenteller.olympic.manage.plyers.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayerId is a Querydsl query type for PlayerId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPlayerId extends BeanPath<PlayerId> {

    private static final long serialVersionUID = -675622052L;

    public static final QPlayerId playerId = new QPlayerId("playerId");

    public final NumberPath<Long> value = createNumber("value", Long.class);

    public QPlayerId(String variable) {
        super(PlayerId.class, forVariable(variable));
    }

    public QPlayerId(Path<? extends PlayerId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayerId(PathMetadata metadata) {
        super(PlayerId.class, metadata);
    }

}


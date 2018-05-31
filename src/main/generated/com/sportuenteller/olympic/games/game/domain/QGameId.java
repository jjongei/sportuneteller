package com.sportuenteller.olympic.games.game.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGameId is a Querydsl query type for GameId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGameId extends BeanPath<GameId> {

    private static final long serialVersionUID = -998442794L;

    public static final QGameId gameId = new QGameId("gameId");

    public final NumberPath<Long> value = createNumber("value", Long.class);

    public QGameId(String variable) {
        super(GameId.class, forVariable(variable));
    }

    public QGameId(Path<? extends GameId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGameId(PathMetadata metadata) {
        super(GameId.class, metadata);
    }

}


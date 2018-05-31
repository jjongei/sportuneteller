package com.sportuenteller.olympic.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayers is a Querydsl query type for Players
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPlayers extends BeanPath<Players> {

    private static final long serialVersionUID = -1558348712L;

    public static final QPlayers players = new QPlayers("players");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPlayers(String variable) {
        super(Players.class, forVariable(variable));
    }

    public QPlayers(Path<? extends Players> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayers(PathMetadata metadata) {
        super(Players.class, metadata);
    }

}


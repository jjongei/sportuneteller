package com.sportuenteller.olympic.manage.plyers.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QParticipationGame is a Querydsl query type for ParticipationGame
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QParticipationGame extends BeanPath<ParticipationGame> {

    private static final long serialVersionUID = 1976127827L;

    public static final QParticipationGame participationGame = new QParticipationGame("participationGame");

    public final NumberPath<Long> gameId = createNumber("gameId", Long.class);

    public final StringPath gameNameEn = createString("gameNameEn");

    public final StringPath gameNameKr = createString("gameNameKr");

    public QParticipationGame(String variable) {
        super(ParticipationGame.class, forVariable(variable));
    }

    public QParticipationGame(Path<? extends ParticipationGame> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParticipationGame(PathMetadata metadata) {
        super(ParticipationGame.class, metadata);
    }

}


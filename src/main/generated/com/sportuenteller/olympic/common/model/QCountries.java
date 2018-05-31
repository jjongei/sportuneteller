package com.sportuenteller.olympic.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCountries is a Querydsl query type for Countries
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCountries extends BeanPath<Countries> {

    private static final long serialVersionUID = 300172250L;

    public static final QCountries countries = new QCountries("countries");

    public final StringPath code = createString("code");

    public final StringPath nameEn = createString("nameEn");

    public final StringPath nameKr = createString("nameKr");

    public QCountries(String variable) {
        super(Countries.class, forVariable(variable));
    }

    public QCountries(Path<? extends Countries> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountries(PathMetadata metadata) {
        super(Countries.class, metadata);
    }

}


package com.sportuenteller.olympic.manage.countries.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCountryId is a Querydsl query type for CountryId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCountryId extends BeanPath<CountryId> {

    private static final long serialVersionUID = -550643684L;

    public static final QCountryId countryId = new QCountryId("countryId");

    public final StringPath value = createString("value");

    public QCountryId(String variable) {
        super(CountryId.class, forVariable(variable));
    }

    public QCountryId(Path<? extends CountryId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountryId(PathMetadata metadata) {
        super(CountryId.class, metadata);
    }

}


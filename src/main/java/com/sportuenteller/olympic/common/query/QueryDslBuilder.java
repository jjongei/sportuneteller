package com.sportuenteller.olympic.common.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.Collections;
import java.util.List;

abstract public class QueryDslBuilder<E> extends QueryDslRepositorySupport {

    /**
     * Creates a new {@link QueryDslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public QueryDslBuilder(Class<?> domainClass) {
        super(domainClass);
    }

    protected BooleanBuilder createBuilder(SearchRequest request, EntityPathBase<E> qClass){
        return request != null ? request.buildDynamicQuery(qClass) : new BooleanBuilder();
    }

    protected List<E> findList(EntityPathBase<E> qClass, SearchRequest request, Sort sort) {
        BooleanBuilder builder = this.createBuilder(request, qClass);
        if(sort == null){
            sort = this.getSort();
        }
        if(builder == null){
            return Collections.emptyList();
        }
        JPQLQuery<E> query = this.from(qClass).where(builder);
        query = super.getQuerydsl().applySorting(sort, query);

        List<E> list = query.fetch();

        if(list == null){
            return Collections.emptyList();
        }
        return list;
    }

    protected Page<E> findList(EntityPathBase<E> qClass, SearchRequest request, Pageable pageable) {
        BooleanBuilder builder = this.createBuilder(request, qClass);

        if(pageable.getSort() == null){
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), this.getSort());
        }

        if(builder == null){
            return new PageImpl<E>(Collections.emptyList(), pageable, 0);
        }
        JPQLQuery<E> query = this.from(qClass).where(builder);
        QueryResults<E> result = super.getQuerydsl().applyPagination(pageable, query).fetchResults();

        return new PageImpl<E>(result.getResults(), pageable, result.getTotal());
    }

    abstract protected Sort getSort();
}

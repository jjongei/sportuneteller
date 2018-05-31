package com.sportuenteller.olympic.common.query;

import com.querydsl.core.BooleanBuilder;

abstract public class SearchRequest<T> {
    abstract public BooleanBuilder buildDynamicQuery(T qClass);
}

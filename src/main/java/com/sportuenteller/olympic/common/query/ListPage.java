package com.sportuenteller.olympic.common.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
abstract public class ListPage<D, V, S> implements Serializable {

    abstract protected List<V> convert(List<D> contents);
    abstract protected S initParams();

    protected List<V> contents = null;
    protected S params = null;

    protected ListPage(){
        this.params = this.initParams();
    }
    protected ListPage(List<D> contents, S params){
        this.contents = this.convert(contents);
        this.params = params;
    }

    public boolean isHasContents(){
        return this.contents != null && !this.contents.isEmpty();
    }
}

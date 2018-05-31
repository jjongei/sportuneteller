package com.sportuenteller.olympic.common.query;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
abstract public class ListPaging<D, V, S> implements Serializable {

    abstract protected S initParams();
    abstract protected int initPageCount();
    abstract protected List<V> convert(List<D> contents);


    protected void setSort(Sort sort) {
        Iterator<Sort.Order> orders = sort.iterator();
        while (orders != null && orders.hasNext()) {
            Sort.Order order = orders.next();
            this.sort = order.getProperty();
            this.desc = order.getDirection().equals(Sort.Direction.ASC) ? false : true;
        }
    }
    public PageRequest getPageRequest() {
        return new PageRequest(this.currentPage - 1, this.offset);
    }


    private List<V> contents = null;
    protected S params = null;

    protected ListPaging(){
        this.currentPage = 1;
        this.offset = 20;
        this.contents = new ArrayList<>();
        this.params = this.initParams();
        this.desc = false;
    }

    public ListPaging(int currentPage, int offset){
        this();
        this.currentPage = currentPage;
        this.offset = offset;
    }

    protected ListPaging(Page<D> page, S params, int initPageCount){
        if(page == null){
            return;
        }
        this.params = params;
        this.contents = page.getContent() != null ? this.convert(page.getContent()) : Collections.emptyList();
        int pageCount =  initPageCount !=0 ? initPageCount : this.initPageCount();

        this.currentPage = page.getNumber() + 1;
        this.offset = page.getSize();
        this.total = (int) page.getTotalElements();
        this.hasContent = page.hasContent();
        int totalPage = page.getTotalPages();
        int numberOfCurrentPage = page.getNumberOfElements();
        this.numberTo = (this.currentPage - 1) * offset + 1;
        this.numberFrom = numberTo + numberOfCurrentPage - 1;
        int currentGroup = this.currentPage / pageCount + (this.currentPage % pageCount == 0 ? 0 : 1);
        int lastIndex = this.total / pageCount + (this.total % pageCount == 0 ? 0 : 1);

        int firstGroup = 1;
        int lastGroup = lastIndex / this.offset + (lastIndex % this.offset == 0 ? 0 : 1);

        int previousGroup = currentGroup - 1;
        int nextGroup = currentGroup + 1;

        this.startIndex = currentGroup * pageCount - pageCount + 1;
        this.endIndex = currentGroup * pageCount;

        if (this.endIndex > totalPage) {
            this.endIndex = totalPage;
        }

        this.previousPage = previousGroup * pageCount;
        this.nextPage = nextGroup * pageCount - pageCount + 1;

        this.hasPreviousPage = currentGroup != firstGroup && this.total != 0;
        this.hasNextPage = currentGroup != lastGroup && this.total != 0;

        //## 정렬 정보 구하기
        this.setSort(page.getSort());
    }

    protected int currentPage;
    protected int offset;
    private int total;
    private int numberTo;
    private int numberFrom;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private boolean hasContent;
    private int startIndex;
    private int endIndex;
    private int previousPage;
    private int nextPage;
    protected String sort;
    protected boolean desc;
}

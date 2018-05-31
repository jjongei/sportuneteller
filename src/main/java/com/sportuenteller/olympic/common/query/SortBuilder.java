package com.sportuenteller.olympic.common.query;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class SortBuilder {

    public static Sort build(boolean desc, String order){
        List<Sort.Order> list = new ArrayList<>();
        list.add(createOrder(new Order(desc, order)));
        return new Sort(list);
    }

    public static Sort build(Order[] orders){
        List<Sort.Order> list = new ArrayList<>();
        for(Order order : orders){
            list.add(createOrder(order));
        }
        return new Sort(list);
    }

    private static Sort.Order createOrder(Order order){
        return new Sort.Order(order.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, order.getOrder()).nullsLast();
    }
}

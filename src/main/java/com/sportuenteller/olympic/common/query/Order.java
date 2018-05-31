package com.sportuenteller.olympic.common.query;

import lombok.Data;

@Data
public class Order {
    private boolean desc;
    private String order;

    public Order(boolean desc, String order) {
        this.desc = desc;
        this.order = order;
    }
}

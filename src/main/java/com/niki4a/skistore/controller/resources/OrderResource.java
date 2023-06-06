package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResource {
    private Long orderId;
    private CartResource cart;
    private UserResource user;
    private Date orderDate;
}

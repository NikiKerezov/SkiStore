package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.OrderResource;

import java.util.List;

public interface OrderService {
    List<OrderResource> findAll();
    OrderResource findById(Long id);
    OrderResource save(OrderResource orderResource);
    OrderResource update(OrderResource orderResource);
    void delete(Long id);
}

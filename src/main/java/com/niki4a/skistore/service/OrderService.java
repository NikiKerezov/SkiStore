package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.OrderResource;
import jakarta.persistence.criteria.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderResource> findAll();
    OrderResource findById(Long id);
    OrderResource save(OrderResource orderResource);
    OrderResource update(OrderResource orderResource, Long id);
    void delete(Long id);

    Optional<OrderResource> findByUser(String username);
}

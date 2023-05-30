package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderResource> findAll() {
        return null;
    }

    @Override
    public OrderResource findById(Long id) {
        return null;
    }

    @Override
    public OrderResource save(OrderResource orderResource) {
        return null;
    }

    @Override
    public OrderResource update(OrderResource orderResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

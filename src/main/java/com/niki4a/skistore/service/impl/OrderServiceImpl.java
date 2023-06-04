package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.entity.CustomerOrder;
import com.niki4a.skistore.repository.OrderRepository;
import com.niki4a.skistore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.niki4a.skistore.mapper.CartMapper.CART_MAPPER;
import static com.niki4a.skistore.mapper.OrderMapper.ORDER_MAPPER;
import static com.niki4a.skistore.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<OrderResource> findAll() {
        return ORDER_MAPPER.toOrderResourceList(orderRepository.findAll());
    }

    @Override
    public OrderResource findById(Long id) {
        return ORDER_MAPPER.toOrderResource(orderRepository.findByOrderId(id));
    }

    @Override
    public OrderResource save(OrderResource orderResource) {
        CustomerOrder order = ORDER_MAPPER.fromOrderResource(orderResource);
        return ORDER_MAPPER.toOrderResource(orderRepository.save(order));
    }

    @Override
    public OrderResource update(OrderResource orderResource, Long id) {
        CustomerOrder order = orderRepository.findByOrderId(id);
        order.setOrderDate(orderResource.getOrderDate());
        order.setUser(USER_MAPPER.fromUserResource(orderResource.getUser()));
        order.setCart(CART_MAPPER.fromCartResource(orderResource.getCart()));
        return ORDER_MAPPER.toOrderResource(orderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteByOrderId(id);
    }
}

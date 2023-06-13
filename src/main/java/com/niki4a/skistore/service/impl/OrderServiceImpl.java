package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.entity.CustomerOrder;
import com.niki4a.skistore.mapper.OrderMapper;
import com.niki4a.skistore.repository.CartRepository;
import com.niki4a.skistore.repository.OrderRepository;
import com.niki4a.skistore.repository.UserRepository;
import com.niki4a.skistore.service.OrderService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<OrderResource> findAll() {
        return orderMapper.fromCustomerOrderList(orderRepository.findAll());
    }

    @Override
    public OrderResource findById(Long id) {
        CustomerOrder order = orderRepository.findByOrderId(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.fromCustomerOrder(order);
    }

    @Override
    public OrderResource save(OrderResource orderResource) {
        CustomerOrder order = orderMapper.toCustomerOrder(orderResource);

        cartRepository.findById(orderResource.getCart().getCartId())
                .ifPresentOrElse(order::setCart, () -> {
                    throw new IllegalArgumentException("Cart not found");
                });

        userRepository.findByUsername(orderResource.getUser())
                .ifPresentOrElse(order::setUser, () -> {
                    throw new IllegalArgumentException("User not found");
                });

        return orderMapper.fromCustomerOrder(orderRepository.save(order));
    }

    @Override
    public OrderResource update(OrderResource orderResource, Long id) {
        CustomerOrder order = orderRepository.findByOrderId(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderDate(orderResource.getOrderDate());

        userRepository.findByUsername(orderResource.getUser())
                .ifPresentOrElse(order::setUser, () -> {
                    throw new IllegalArgumentException("User not found");
                });

        cartRepository.findById(orderResource.getCart().getCartId())
                .ifPresentOrElse(order::setCart, () -> {
                    throw new IllegalArgumentException("Cart not found");
                });

        return orderMapper.fromCustomerOrder(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteByOrderId(id);
    }

    @Override
    public Optional<OrderResource> findByUser(String username) {
        return orderRepository.findByUserUsername(username)
                .map(orderMapper::fromCustomerOrder);
    }

    @Override
    public List<OrderResource> findAllAudits(Long id) {
        AuditReader auditReader = org.hibernate.envers.AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(CustomerOrder.class, id);
        List<OrderResource> orderResourceList = new ArrayList<>();

        for (Number revision : revisions) {
            CustomerOrder order = auditReader.find(CustomerOrder.class, id, revision);
            orderResourceList.add(orderMapper.fromCustomerOrder(order));
        }

        return orderResourceList;
    }
}

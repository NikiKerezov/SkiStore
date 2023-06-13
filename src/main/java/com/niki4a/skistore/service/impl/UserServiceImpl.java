package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.entity.CustomerOrder;
import com.niki4a.skistore.entity.User;
import com.niki4a.skistore.mapper.UserMapper;
import com.niki4a.skistore.repository.OrderRepository;
import com.niki4a.skistore.repository.UserRepository;
import com.niki4a.skistore.service.UserService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<UserResource> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResource)
                .collect(Collectors.toList());
    }

    @Override
    public UserResource findById(Long id) {
        return userMapper.toUserResource(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public UserResource save(UserResource userResource) {
        User user = userMapper.fromUserResource(userResource);
        user.setOrders(new HashSet<>());
        return userMapper.toUserResource(userRepository.save(user));
    }

    @Override
    public UserResource update(UserResource userResource, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(userResource.getUsername());
        user.setPassword(userResource.getPassword());
        user.setEmail(userResource.getEmail());
        user.setAddress(userResource.getAddress());
        user.setCorporationName(userResource.getCorporationName());

        if (userResource.getOrders() != null) {
            Set<CustomerOrder> orders = new HashSet<>();
            for (OrderResource orderResource : userResource.getOrders()) {
                orderRepository.findById(orderResource.getOrderId())
                        .ifPresentOrElse(orders::add, () -> {
                            throw new IllegalArgumentException("Order not found");
                        });
            }
            user.setOrders(orders);
        }

        return userMapper.toUserResource(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        orderRepository.deleteAll(user.getOrders());
        user.setOrders(new HashSet<>());
        userRepository.delete(user);
    }

    @Override
    public Optional<UserResource> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserResource);
    }

    @Override
    public List<UserResource> findAllAudits(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(User.class, id);
        List<UserResource> userResources = new ArrayList<>();

        for (Number revision : revisions) {
            User user = auditReader.find(User.class, id, revision);
            userResources.add(userMapper.toUserResource(user));
        }

        return userResources;
    }
}

package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.entity.User;
import com.niki4a.skistore.mapper.UserMapper;
import com.niki4a.skistore.repository.OrderRepository;
import com.niki4a.skistore.repository.UserRepository;
import com.niki4a.skistore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private final UserMapper USER_MAPPER;

    @Override
    public List<UserResource> findAll() {
        return USER_MAPPER.toUserResourceList(userRepository.findAll());
    }

    @Override
    public UserResource findById(Long id) {
        return USER_MAPPER.toUserResource(userRepository.findByUserId(id));
    }

    @Override
    public UserResource save(UserResource userResource) {
        User user = USER_MAPPER.fromUserResource(userResource);
        user.setOrders(null);
        return USER_MAPPER.toUserResource(userRepository.save(user));
    }
// private String username;
//    private String password;
//    private String email;
//    private String address;
//    private String corporationName;
    @Override
    public UserResource update(UserResource userResource, Long id) {
        User user = userRepository.findByUserId(id);
        user.setUsername(userResource.getUsername());
        user.setPassword(userResource.getPassword());
        user.setEmail(userResource.getEmail());
        user.setAddress(userResource.getAddress());
        user.setCorporationName(userResource.getCorporationName());

        if (userResource.getOrders() == null) {
            user.setOrders(new HashSet<>());
        }

        for (OrderResource orderResource : userResource.getOrders()) {
            orderRepository.findByUserUsername(userResource.getUsername()).ifPresentOrElse(
                    user::addOrder,
                    () -> {
                        throw new IllegalArgumentException("Order not found");
                    });
        }

        return USER_MAPPER.toUserResource(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteByUserId(id);
    }

    @Override
    public Optional<UserResource> findByUsername(String username) {
        return Optional.of(USER_MAPPER.toUserResource(userRepository.findByUsername(username).get()));
    }
}

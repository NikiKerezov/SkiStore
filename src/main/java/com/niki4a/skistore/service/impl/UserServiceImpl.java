package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.entity.CustomerOrder;
import com.niki4a.skistore.entity.User;
import com.niki4a.skistore.repository.UserRepository;
import com.niki4a.skistore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.niki4a.skistore.mapper.OrderMapper.ORDER_MAPPER;
import static com.niki4a.skistore.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
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
        Set<CustomerOrder> orderList = new HashSet<>();
        for (OrderResource order : userResource.getOrders()) {
            orderList.add(ORDER_MAPPER.toCustomerOrder(order));
        }

        return USER_MAPPER.toUserResource(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteByUserId(id);
    }
}

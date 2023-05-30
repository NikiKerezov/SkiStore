package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public List<UserResource> findAll() {
        return null;
    }

    @Override
    public UserResource findById(Long id) {
        return null;
    }

    @Override
    public UserResource save(UserResource userResource) {
        return null;
    }

    @Override
    public UserResource update(UserResource userResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

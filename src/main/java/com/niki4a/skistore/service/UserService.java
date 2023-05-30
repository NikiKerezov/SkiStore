package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.UserResource;

import java.util.List;

public interface UserService {
    List<UserResource> findAll();
    UserResource findById(Long id);
    UserResource save(UserResource userResource);
    UserResource update(UserResource userResource);
    void delete(Long id);
}

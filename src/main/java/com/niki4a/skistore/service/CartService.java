package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.CartResource;

import java.util.List;

public interface CartService {
    List<CartResource> findAll();
    CartResource findById(Long id);
    CartResource save(CartResource cartResource);
    CartResource update(CartResource cartResource);
    void delete(Long id);
}

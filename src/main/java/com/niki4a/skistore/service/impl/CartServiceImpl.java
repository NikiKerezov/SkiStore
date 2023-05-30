package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Override
    public List<CartResource> findAll() {
        return null;
    }

    @Override
    public CartResource findById(Long id) {
        return null;
    }

    @Override
    public CartResource save(CartResource cartResource) {
        return null;
    }

    @Override
    public CartResource update(CartResource cartResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

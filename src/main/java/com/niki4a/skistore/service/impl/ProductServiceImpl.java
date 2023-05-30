package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductResource> findAll() {
        return null;
    }

    @Override
    public ProductResource findById(Long id) {
        return null;
    }

    @Override
    public ProductResource save(ProductResource productResource) {
        return null;
    }

    @Override
    public ProductResource update(ProductResource productResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

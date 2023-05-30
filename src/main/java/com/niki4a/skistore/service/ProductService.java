package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.ProductResource;

import java.util.List;

public interface ProductService {
    List<ProductResource> findAll();
    ProductResource findById(Long id);
    ProductResource save(ProductResource productResource);
    ProductResource update(ProductResource productResource);
    void delete(Long id);
}

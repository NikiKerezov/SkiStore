package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.ProductResource;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResource> findAll();
    ProductResource findById(Long id);
    ProductResource save(ProductResource productResource);
    ProductResource update(ProductResource productResource, Long id);
    void delete(Long id);

    Optional<ProductResource> findByProductName(String username);

    List<ProductResource> findAllAudits(Long id);
}

package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.CategoryResource;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResource> findAll();
    CategoryResource findById(Long id);
    CategoryResource save(CategoryResource categoryResource);
    CategoryResource update(CategoryResource categoryResource, Long id);
    void delete(Long id);

    Optional<CategoryResource> findByCategoryName(String name);
}

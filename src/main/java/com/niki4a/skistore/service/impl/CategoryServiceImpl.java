package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryResource> findAll() {
        return null;
    }

    @Override
    public CategoryResource findById(Long id) {
        return null;
    }

    @Override
    public CategoryResource save(CategoryResource categoryResource) {
        return null;
    }

    @Override
    public CategoryResource update(CategoryResource categoryResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Category;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.repository.CategoryRepository;
import com.niki4a.skistore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.niki4a.skistore.mapper.CategoryMapper.CATEGORY_MAPPER;
import static com.niki4a.skistore.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryResource> findAll() {
        return CATEGORY_MAPPER.toCategoryResourceList(categoryRepository.findAll());
    }

    @Override
    public CategoryResource findById(Long id) {
        return CATEGORY_MAPPER.toCategoryResource(categoryRepository.findByCategoryId(id));
    }

    @Override
    public CategoryResource save(CategoryResource categoryResource) {
        Category category = CATEGORY_MAPPER.fromCategoryResource(categoryResource);
        return CATEGORY_MAPPER.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    public CategoryResource update(CategoryResource categoryResource, Long id) {
        Category category = categoryRepository.findByCategoryId(id);
        category.setCategoryName(categoryResource.getCategoryName());
        Set<Product> productList = new HashSet<>();
        for (ProductResource product : categoryResource.getProducts()) {
            productList.add(PRODUCT_MAPPER.fromProductResource(product));
        }
        return CATEGORY_MAPPER.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteByCategoryId(id);
    }
}

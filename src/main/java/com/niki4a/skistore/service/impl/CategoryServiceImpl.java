package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Category;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.mapper.CategoryMapper;
import com.niki4a.skistore.repository.CategoryRepository;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryMapper CATEGORY_MAPPER;

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

        if (categoryRepository.findByCategoryName(categoryResource.getCategoryName()).isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }

        category.setProducts(null);
        return CATEGORY_MAPPER.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    public CategoryResource update(CategoryResource categoryResource, Long id) {
        Category category = categoryRepository.findByCategoryId(id);
        category.setCategoryName(categoryResource.getCategoryName());
        Set<Product> productList = new HashSet<>();
        for (ProductResource product : categoryResource.getProducts()) {
            productRepository.findByProductName(product.getProductName()).ifPresentOrElse(
                    category::addProduct,
                    () -> {
                        throw new IllegalArgumentException("Product not found");
                    });
        }
        return CATEGORY_MAPPER.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteByCategoryId(id);
    }

    @Override
    public Optional<CategoryResource> findByCategoryName(String categoryName) {
        return Optional.ofNullable(CATEGORY_MAPPER.toCategoryResource(categoryRepository.findByCategoryName(categoryName).get()));
    }
}

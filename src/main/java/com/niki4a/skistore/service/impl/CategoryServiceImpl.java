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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResource> findAll() {
        return categoryMapper.toCategoryResourceList(categoryRepository.findAll());
    }

    @Override
    public CategoryResource findById(Long id) {
        Category category = categoryRepository.findByCategoryId(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return categoryMapper.toCategoryResource(category);
    }

    @Override
    public CategoryResource save(CategoryResource categoryResource) {
        if (categoryRepository.findByCategoryName(categoryResource.getCategoryName()).isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = categoryMapper.fromCategoryResource(categoryResource);
        category.setProducts(new HashSet<>());

        return categoryMapper.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    public CategoryResource update(CategoryResource categoryResource, Long id) {
        Category category = categoryRepository.findByCategoryId(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setCategoryName(categoryResource.getCategoryName());

        Set<Product> productList = new HashSet<>();
        for (ProductResource product : categoryResource.getProducts()) {
            Product existingProduct = productRepository.findByProductName(product.getProductName())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            productList.add(existingProduct);
        }
        category.setProducts(productList);

        return categoryMapper.toCategoryResource(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteByCategoryId(id);
    }

    @Override
    public Optional<CategoryResource> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .map(categoryMapper::toCategoryResource);
    }
}

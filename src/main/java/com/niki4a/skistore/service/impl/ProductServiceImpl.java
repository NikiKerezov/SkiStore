package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Category;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.entity.Tag;
import com.niki4a.skistore.mapper.ProductMapper;
import com.niki4a.skistore.repository.CategoryRepository;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.repository.TagRepository;
import com.niki4a.skistore.service.CategoryService;
import com.niki4a.skistore.service.ProductService;
import com.niki4a.skistore.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ProductMapper PRODUCT_MAPPER;

    @Override
    public List<ProductResource> findAll() {
        return PRODUCT_MAPPER.toProductResourceList(productRepository.findAll());
    }

    @Override
    public ProductResource findById(Long id) {
        return PRODUCT_MAPPER.toProductResource(productRepository.findByProductId(id));
    }

    @Override
    public ProductResource save(ProductResource productResource) {
        Product product = PRODUCT_MAPPER.fromProductResource(productResource);

        for (String tag : productResource.getTags()) {
            tagRepository.findByTagName(tag).ifPresentOrElse(
                    product::addTag,
                    () -> {
                        throw new IllegalArgumentException("Tag not found");
                    });
        }

        categoryRepository.findByCategoryName(productResource.getCategory()).ifPresentOrElse(
                product::setCategory,
                () -> {
                    throw new IllegalArgumentException("Category not found");
                });

        categoryRepository.findByCategoryName(productResource.getCategory()).get().addProduct(product);
        return PRODUCT_MAPPER.toProductResource(productRepository.save(product));
    }

    @Override
    public ProductResource update(ProductResource productResource, Long id) {
        Product product = productRepository.findByProductId(id);
        product.setProductName(productResource.getProductName());
        product.setDescription(productResource.getDescription());
        product.setPrice(productResource.getPrice());
        product.setQuantityInStock(productResource.getQuantityInStock());
        product.setPrice(productResource.getPrice());

        for (String tag : productResource.getTags()) {
            tagRepository.findByTagName(tag).ifPresentOrElse(
                    product::addTag,
                    () -> {
                        throw new IllegalArgumentException("Tag not found");
                    });
        }

        categoryRepository.findByCategoryName(productResource.getCategory()).ifPresentOrElse(
                product::setCategory,
                () -> {
                    throw new IllegalArgumentException("Category not found");
                });

        return PRODUCT_MAPPER.toProductResource(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteByProductId(id);
    }

    @Override
    public Optional<ProductResource> findByProductName(String productName) {
        return Optional.ofNullable(PRODUCT_MAPPER.toProductResource(productRepository.findByProductName(productName).get()));
    }
}

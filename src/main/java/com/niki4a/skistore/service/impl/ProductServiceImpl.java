package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Category;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.entity.Tag;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.niki4a.skistore.mapper.CategoryMapper.CATEGORY_MAPPER;
import static com.niki4a.skistore.mapper.ProductMapper.PRODUCT_MAPPER;
import static com.niki4a.skistore.mapper.TagMapper.TAG_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
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
        return PRODUCT_MAPPER.toProductResource(productRepository.save(product));
    }

    @Override
    public ProductResource update(ProductResource productResource, Long id) {
        Product product = productRepository.findByProductId(id);
        product.setProductName(productResource.getProductName());
        product.setDescription(productResource.getDescription());
        product.setPrice(productResource.getPrice());
        product.setCategory(new Category() {
            {
                setCategoryName(productResource.getCategory());
                setProducts(null);
                setCategoryId(null);
            }
        });
        Set<Tag> tagList = new HashSet<>();
        for (String tag : productResource.getTags()) {
            tagList.add(new Tag() {
                {
                    setTagName(tag);
                    setProducts(null);
                    setTagId(null);
                }
            });
        }
        product.setTags(tagList);
        return PRODUCT_MAPPER.toProductResource(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteByProductId(id);
    }
}

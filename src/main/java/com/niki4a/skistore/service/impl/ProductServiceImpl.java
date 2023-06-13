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
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<ProductResource> findAll() {
        return productMapper.toProductResourceList(productRepository.findAll());
    }

    @Override
    public ProductResource findById(Long id) {
        Product product = productRepository.findByProductId(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productMapper.toProductResource(product);
    }

    @Override
    public ProductResource save(ProductResource productResource) {
        Product product = productMapper.fromProductResource(productResource);

        product.setTags(new HashSet<>());

        for (String tag : productResource.getTags()) {
            Tag existingTag = tagRepository.findByTagName(tag)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found"));
            product.addTag(existingTag);
        }

        Category category = categoryRepository.findByCategoryName(productResource.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        product.setCategory(category);
        category.addProduct(product);

        return productMapper.toProductResource(productRepository.save(product));
    }

    @Override
    public ProductResource update(ProductResource productResource, Long id) {
        Product product = productRepository.findByProductId(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setProductName(productResource.getProductName());
        product.setDescription(productResource.getDescription());
        product.setPrice(productResource.getPrice());
        product.setQuantityInStock(productResource.getQuantityInStock());

        if (productResource.getTags() != null) {
            Set<Tag> tagList = new HashSet<>();
            for (String tagName : productResource.getTags()) {
                Tag existingTag = tagRepository.findByTagName(tagName)
                        .orElseThrow(() -> new IllegalArgumentException("Tag not found"));
                tagList.add(existingTag);
            }
            product.setTags(tagList);
        }

        Category category = categoryRepository.findByCategoryName(productResource.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        product.setCategory(category);

        return productMapper.toProductResource(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findByProductId(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.getTags().forEach(tag -> tag.removeProduct(product));
        product.setTags(new HashSet<>());
        product.setCategory(null);
        productRepository.delete(product);
    }

    @Override
    public Optional<ProductResource> findByProductName(String productName) {
        return productRepository.findByProductName(productName)
                .map(productMapper::toProductResource);
    }

    @Override
    public List<ProductResource> findAllAudits(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(Product.class, id);
        List<ProductResource> productResourceList = new ArrayList<>();

        for (Number revision : revisions) {
            Product product = auditReader.find(Product.class, id, revision);
            productResourceList.add(productMapper.toProductResource(product));
        }

        return productResourceList;
    }


}

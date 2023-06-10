package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.entity.Tag;
import com.niki4a.skistore.mapper.TagMapper;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.repository.TagRepository;
import com.niki4a.skistore.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProductRepository productRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagResource> findAll() {
        return tagMapper.toTagResourceList(tagRepository.findAll());
    }

    @Override
    public Optional<TagResource> findById(Long id) {
        Tag tag = tagRepository.findByTagId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found"));
        return Optional.of(tagMapper.toTagResource(tag));
    }

    @Override
    public TagResource save(TagResource tagResource) {
        Tag tag = tagMapper.fromTagResource(tagResource);
        tag.setProducts(null);
        return tagMapper.toTagResource(tagRepository.save(tag));
    }

    @Override
    public TagResource update(TagResource tagResource, Long id) {
        Tag tag = tagRepository.findByTagId(id).orElseThrow(() -> new IllegalArgumentException("Tag not found"));

        tag.setTagName(tagResource.getTagName());

        for (ProductResource productResource : tagResource.getProducts()) {
            Product existingProduct = productRepository.findByProductName(productResource.getProductName())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            tag.addProduct(existingProduct);
        }

        return tagMapper.toTagResource(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Tag tag = tagRepository.findByTagId(id).orElseThrow(() -> new IllegalArgumentException("Tag not found"));
        tag.getProducts().forEach(product -> product.removeTag(tag));
        tag.setProducts(new HashSet<>());
        tagRepository.delete(tag);
    }

    @Override
    public Optional<TagResource> findByName(String name) {
        return tagRepository.findByTagName(name)
                .map(tagMapper::toTagResource);
    }
}

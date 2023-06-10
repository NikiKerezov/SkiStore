package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Tag;
import com.niki4a.skistore.mapper.TagMapper;
import com.niki4a.skistore.repository.ProductRepository;
import com.niki4a.skistore.repository.TagRepository;
import com.niki4a.skistore.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProductRepository productRepository;

    @Autowired
    private final TagMapper TAG_MAPPER;

    @Override
    public List<TagResource> findAll() {
        return TAG_MAPPER.toTagResourceList(tagRepository.findAll());
    }

    @Override
    public TagResource findById(Long id) {
        return TAG_MAPPER.toTagResource(tagRepository.findByTagId(id));
    }

    @Override
    public TagResource save(TagResource tagResource) {
        Tag tag = TAG_MAPPER.fromTagResource(tagResource);
        tag.setProducts(null);
        return TAG_MAPPER.toTagResource(tagRepository.save(tag));
    }

    @Override
    public TagResource update(TagResource tagResource, Long id) {
        Tag tag = tagRepository.findByTagId(id);
        tag.setTagName(tagResource.getTagName());

        for (ProductResource productResource : tagResource.getProducts()) {
            productRepository.findByProductName(productResource.getProductName()).ifPresentOrElse(
                    tag::addProduct,
                    () -> {
                        throw new IllegalArgumentException("Product not found");
                    });
        }

        return TAG_MAPPER.toTagResource(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagRepository.deleteByTagId(id);
    }

    @Override
    public Optional<TagResource> findByName(String name) {
        return Optional.ofNullable(TAG_MAPPER.toTagResource(tagRepository.findByTagName(name).get()));
    }
}

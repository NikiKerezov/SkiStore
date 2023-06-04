package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.entity.Tag;
import com.niki4a.skistore.repository.TagRepository;
import com.niki4a.skistore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.niki4a.skistore.mapper.ProductMapper.PRODUCT_MAPPER;
import static com.niki4a.skistore.mapper.TagMapper.TAG_MAPPER;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;
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
        return TAG_MAPPER.toTagResource(tagRepository.save(tag));
    }

    @Override
    public TagResource update(TagResource tagResource, Long id) {
        Tag tag = tagRepository.findByTagId(id);
        tag.setTagName(tagResource.getTagName());
        Set<Product> productList = new HashSet<>();
        for (ProductResource product : tagResource.getProducts()) {
            productList.add(PRODUCT_MAPPER.fromProductResource(product));
        }

        return TAG_MAPPER.toTagResource(tagRepository.save(tag));
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteByTagId(id);
    }
}

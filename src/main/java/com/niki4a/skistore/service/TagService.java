package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.TagResource;

import java.util.List;

public interface TagService {
    List<TagResource> findAll();
    TagResource findById(Long id);
    TagResource save(TagResource tagResource);
    TagResource update(TagResource tagResource, Long id);
    void delete(Long id);
}

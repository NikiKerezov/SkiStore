package com.niki4a.skistore.service;

import com.niki4a.skistore.controller.resources.TagResource;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TagService {
    List<TagResource> findAll();
    TagResource findById(Long id);
    TagResource save(TagResource tagResource);
    TagResource update(TagResource tagResource, Long id);
    void delete(Long id);

    Optional<TagResource> findByName(String name);
}

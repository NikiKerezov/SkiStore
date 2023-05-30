package com.niki4a.skistore.service.impl;

import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    @Override
    public List<TagResource> findAll() {
        return null;
    }

    @Override
    public TagResource findById(Long id) {
        return null;
    }

    @Override
    public TagResource save(TagResource tagResource) {
        return null;
    }

    @Override
    public TagResource update(TagResource tagResource) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

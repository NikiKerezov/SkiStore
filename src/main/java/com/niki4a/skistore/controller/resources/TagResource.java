package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagResource {
    private Long tagId;
    private String tagName;
    private Set<ProductResource> products = new HashSet<>();
}

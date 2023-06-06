package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryResource {
    private Long categoryId;
    private String categoryName;
    private Set<ProductResource> products = new HashSet<>();
}

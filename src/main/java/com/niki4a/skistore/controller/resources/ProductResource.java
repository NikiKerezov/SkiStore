package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductResource {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int quantityInStock;
    private String category;
    private Set<String> tags;

    public Set<String> getTags() {
        if (tags == null) {
            tags = new HashSet<>();
        }
        return tags;
    }
}

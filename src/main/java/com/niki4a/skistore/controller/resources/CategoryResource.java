package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryResource {
    //public class Category {
    //    @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long categoryId;
    //
    //    private String categoryName;
    //
    //    @OneToMany(mappedBy = "category")
    //    private Set<Product> products = new HashSet<>();
    //
    //    // getters and setters
    //}

    private Long categoryId;
    private String categoryName;
    private Set<ProductResource> products = new HashSet<>();
}

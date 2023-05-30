package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductResource {
    //public class Product {
    //    @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long productId;
    //
    //    private String productName;
    //    private String description;
    //    private double price;
    //    private int quantityInStock;
    //
    //    @ManyToOne
    //    @JoinColumn(name = "categoryId")
    //    private Category category;
    //
    //   @ManyToMany
    //    @JoinTable(
    //            name = "product_tag",
    //            joinColumns = @JoinColumn(name = "productId"),
    //            inverseJoinColumns = @JoinColumn(name = "tagId"))
    //    private Set<Tag> tags = new HashSet<>();
    //
    //    // getters and setters
    //}

    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int quantityInStock;
    private CategoryResource category;
    private Set<TagResource> tags = new HashSet<>();
}

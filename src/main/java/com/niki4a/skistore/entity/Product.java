package com.niki4a.skistore.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;

    private String productName;
    private String description;
    private double price;
    private int quantityInStock;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<Tag> tags = new HashSet<>();

    // getters and setters
}

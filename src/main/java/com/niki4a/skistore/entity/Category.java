package com.niki4a.skistore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    // getters and setters
}

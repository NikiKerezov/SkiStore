package com.niki4a.skistore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long tagId;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Product> products = new HashSet<>();

    // getters and setters
}

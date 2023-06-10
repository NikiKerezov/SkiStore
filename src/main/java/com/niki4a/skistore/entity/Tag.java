package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.ProductResource;
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getTags().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getTags().remove(this);
    }


    // getters and setters
}

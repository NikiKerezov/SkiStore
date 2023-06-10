package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.ProductResource;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cartId;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cartId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products;

    private double totalAmount;

    public void addProduct(Product product) {
        products.add(product);
    }

    // getters and setters
}

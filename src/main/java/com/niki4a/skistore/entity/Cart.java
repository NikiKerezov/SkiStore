package com.niki4a.skistore.entity;

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

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cartId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products = new HashSet<>();

    private double totalAmount;

    // getters and setters
}

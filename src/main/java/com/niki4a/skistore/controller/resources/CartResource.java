package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CartResource {
    //public class Cart {
    //    @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long cartId;
    //
    //    @ManyToMany
    //    @JoinTable(
    //            name = "cart_product",
    //            joinColumns = @JoinColumn(name = "cartId"),
    //            inverseJoinColumns = @JoinColumn(name = "productId"))
    //    private Set<Product> products = new HashSet<>();
    //
    //    private double totalAmount;
    //
    //    // getters and setters
    //}

    private Long cartId;
    private Set<ProductResource> products = new HashSet<>();
    private double totalAmount;
}

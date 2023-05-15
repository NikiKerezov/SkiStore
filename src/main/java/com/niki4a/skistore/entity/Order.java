package com.niki4a.skistore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products = new HashSet<>();

    private Date orderDate;
    private double totalAmount;

    // getters and setters
}

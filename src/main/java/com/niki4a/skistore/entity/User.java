package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.OrderResource;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String address;
    private String corporationName;

    @OneToMany(mappedBy = "user")
    private Set<CustomerOrder> orders;

    public void addOrder(CustomerOrder order) {
        orders.add(order);
    }

    // getters and setters
}

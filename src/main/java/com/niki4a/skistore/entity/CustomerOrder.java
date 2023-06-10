package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.controller.resources.UserResource;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    private User user;

    private Date orderDate;

    // getters and setters
}

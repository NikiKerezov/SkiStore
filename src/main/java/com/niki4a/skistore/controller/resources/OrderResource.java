package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResource {
    //    @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long orderId;
    //
    //    @OneToOne
    //    @JoinColumn(name = "cartId")
    //    private Cart cart;
    //
    //    @ManyToOne
    //    private User user;
    //
    //    private Date orderDate;
    //
    //    // getters and setters

    private Long orderId;
    private CartResource cart;
    private UserResource user;
    private Date orderDate;
}

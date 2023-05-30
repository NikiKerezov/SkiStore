package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResource {
    // @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long userId;
    //
    //    private String username;
    //    private String password;
    //    private String email;
    //    private String address;
    //    private String corporationName;
    //
    //    @OneToMany(mappedBy = "user")
    //    private Set<CustomerOrder> orders = new HashSet<>();
    //
    //    // getters and setters

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String corporationName;
    private Set<OrderResource> orders = new HashSet<>();
}

package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserResource {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String corporationName;
    private Set<OrderResource> orders;
    private Date createdDate;
}

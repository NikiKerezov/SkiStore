package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class CartResource {
    private Long cartId;
    private Set<ProductResource> products;
    private double totalAmount;
    private Date createdDate;
}

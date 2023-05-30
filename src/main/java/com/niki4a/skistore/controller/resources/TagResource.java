package com.niki4a.skistore.controller.resources;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagResource {
    //public class Tag {
    //    @Id
    //    @GeneratedValue(strategy=GenerationType.AUTO)
    //    private Long tagId;
    //
    //    private String tagName;
    //
    //    @ManyToMany(mappedBy = "tags")
    //    private Set<Product> products = new HashSet<>();
    //
    //    // getters and setters
    //}

    private Long tagId;
    private String tagName;
    private Set<ProductResource> products = new HashSet<>();
}

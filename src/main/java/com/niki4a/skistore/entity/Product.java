package com.niki4a.skistore.entity;
import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.controller.resources.TagResource;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;


@Entity
@Getter
@Setter
@ToString
@Audited
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;

    private String productName;
    private String description;
    private double price;
    private int quantityInStock;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<Tag> tags;

    public Set<Tag> getTags() {
        if (tags == null) {
            tags = new HashSet<>();
        }
        return tags;
    }

    @CreatedDate
    private Date createdDate;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void preAnyAction() {
        createdDate = new Date();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProducts().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getProducts().remove(this);
    }


    // getters and setters
}

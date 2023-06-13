package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.CartResource;
import com.niki4a.skistore.controller.resources.UserResource;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Audited
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

    @CreatedDate
    Date createdDate;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void preAnyAction() {
        createdDate = new Date();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cart == null) ? 0 : cart.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerOrder other = (CustomerOrder) obj;
        if (cart == null) {
            if (other.cart != null)
                return false;
        } else if (!cart.equals(other.cart))
            return false;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
            return false;
        return true;
    }


    // getters and setters
}

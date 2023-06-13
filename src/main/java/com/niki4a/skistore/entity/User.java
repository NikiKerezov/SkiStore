package com.niki4a.skistore.entity;

import com.niki4a.skistore.controller.resources.OrderResource;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Audited
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String corporationName;

    @CreatedDate
    private Date createdDate;

    @OneToMany(mappedBy = "user")
    private Set<CustomerOrder> orders;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void preAnyAction() {
        createdDate = new Date();
    }

    public void addOrder(CustomerOrder order) {
        orders.add(order);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((corporationName == null) ? 0 : corporationName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (corporationName == null) {
            if (other.corporationName != null)
                return false;
        } else if (!corporationName.equals(other.corporationName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }


    // getters and setters
}

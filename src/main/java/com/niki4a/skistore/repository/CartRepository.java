package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCartId(Long id);
    List<Cart> findAllByCartIdExists();
    void deleteByCartId(Long id);

}

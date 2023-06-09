package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(Long id);
    void deleteByProductId(Long id);
    Optional<Product> findByProductName(String username);
}


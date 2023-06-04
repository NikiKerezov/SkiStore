package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long id);
    void deleteByProductId(Long id);
}


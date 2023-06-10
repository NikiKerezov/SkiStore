package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryId(Long id);
    void deleteByCategoryId(Long id);

    Optional<Category> findByCategoryName(String categoryName);
}

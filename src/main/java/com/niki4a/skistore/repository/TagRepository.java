package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagId(Long id);
    void deleteByTagId(Long id);

    Optional<Tag> findByTagName(String name);
}

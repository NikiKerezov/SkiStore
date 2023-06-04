package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
    User findByUsername(String username);
    void deleteByUserId(Long id);
}

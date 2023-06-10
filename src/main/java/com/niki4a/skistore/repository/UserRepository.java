package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long id);
    Optional<User> findByUsername(String username);
    void deleteByUserId(Long id);
}

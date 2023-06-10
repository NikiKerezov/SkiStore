package com.niki4a.skistore.repository;

import com.niki4a.skistore.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    Optional<CustomerOrder> findByOrderId(Long id);
    void deleteByOrderId(Long id);

    Optional<CustomerOrder> findByUserUsername(String username);
}

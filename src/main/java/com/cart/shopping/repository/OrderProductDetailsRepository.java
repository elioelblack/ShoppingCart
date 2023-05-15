package com.cart.shopping.repository;

import com.cart.shopping.model.OrderProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDetailsRepository extends JpaRepository<OrderProductDetails, Integer> {
}

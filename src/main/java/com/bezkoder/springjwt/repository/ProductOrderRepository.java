package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.ProductOrderInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrderInvoice, Long> {
}

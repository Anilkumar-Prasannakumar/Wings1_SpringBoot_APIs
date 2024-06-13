package com.online.ecommerce.repository;

import com.online.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findByProductCode(int productCode);
}

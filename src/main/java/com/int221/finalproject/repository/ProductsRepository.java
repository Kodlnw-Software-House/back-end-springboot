package com.int221.finalproject.repository;

import com.int221.finalproject.models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
    Page<Products> findByProductNameContains(String productName, Pageable pageable);
}

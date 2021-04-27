package com.int221.finalproject.repository;

import com.int221.finalproject.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
}

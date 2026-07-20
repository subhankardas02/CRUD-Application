package com.project.repository;

import com.project.dto.ProductResponse;
import com.project.model.Product;
import com.project.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    ******************Jpa is smart enough to decode findByActiveTrue Methode**********************
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM product_table p WHERE p.active = true AND p.stockQuantity>0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
//    *****************************************************************************************
}

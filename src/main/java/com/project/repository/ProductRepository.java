package com.project.repository;

import com.project.dto.ProductResponse;
import com.project.model.Product;
import com.project.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    ******************Jpa is smart enough to decode findByActiveTrue Methode**********************
    List<Product> findByActiveTrue();
//    *****************************************************************************************
}

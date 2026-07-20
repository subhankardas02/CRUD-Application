package com.project.service;

import com.project.dto.ProductRequest;
import com.project.dto.ProductResponse;
import com.project.model.Product;
import com.project.repository.ProductRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product=new Product();
        updateProductFromRequest(product, productRequest);
        Product productSave=productRepository.save(product);
        return mapToProductResponse(productSave);

    }

    private ProductResponse mapToProductResponse(Product productSave) {
        ProductResponse response=new ProductResponse();
        response.setId(productSave.getId());
        response.setName(productSave.getName());
        response.setDescription(productSave.getDescription());
        response.setCategory(productSave.getCategory());
        response.setStockQuantity(productSave.getStockQuantity());
        response.setProductUrl(productSave.getProductUrl());
        response.setActive(productSave.isActive());
        return response;

    }


    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setProductUrl(productRequest.getProductUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setPrice(productRequest.getPrice());
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingProduct->{updateProductFromRequest(existingProduct, productRequest);
                Product saveProduct= productRepository.save(existingProduct);
                return mapToProductResponse(saveProduct);
                });
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(Product->{Product.setActive(false);
                productRepository.save(Product);
                return true;}).orElse(false);

    }


    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

    }
}

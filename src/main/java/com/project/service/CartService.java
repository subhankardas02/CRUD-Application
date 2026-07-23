package com.project.service;

import com.project.dto.CartItemRequest;
import com.project.dto.ProductResponse;
import com.project.model.Product;
import com.project.repository.CartItemRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductResponse productResponse;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;

    public void addToCart(String userId, CartItemRequest request) {
        Optional<Product> productOpt=userRepository.findById(request.getProductId());


    }
}

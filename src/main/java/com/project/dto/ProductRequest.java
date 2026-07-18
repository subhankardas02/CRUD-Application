package com.project.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private String productUrl;
    private Integer stockQuantity;
    private BigDecimal price;
}

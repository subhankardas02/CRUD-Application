package com.project.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String productUrl;
    private Integer stockQuantity;
    private BigDecimal price;
    private boolean active;

}

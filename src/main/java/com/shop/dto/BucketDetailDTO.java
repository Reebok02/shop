package com.shop.dto;


import com.shop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {
    private String title;
    private Long productId;
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;

    public BucketDetailDTO (Product product){
        this.name = product.getName();
        this.title = product.getTitle();
        this.amount = new BigDecimal(1.0);
        this.productId = product.getId();
        this.price = product.getPrice();
        this.sum = Double.valueOf(product.getPrice().toString());
    }
}

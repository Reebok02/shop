package com.shop.service;

import com.shop.domain.Bucket;
import com.shop.domain.Product;
import com.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();

    void addToUserBucket(Long productId, String username);

    void editProduct(ProductDTO productDTO);

    boolean saveProduct(ProductDTO productDTO);

    ProductDTO getProduct(Long id);
}

package com.shop.dao;

import com.shop.domain.Category;
import com.shop.domain.Product;
import com.shop.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

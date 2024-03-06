package com.shop.service;

import com.shop.dao.ProductRepository;
import com.shop.domain.*;
import com.shop.dto.ProductDTO;
import com.shop.dto.UserDTO;
import com.shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found by name: " + username);
        }

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @Transactional
    public void editProduct(ProductDTO dto) {
        Product savedProduct = productRepository.getReferenceById(dto.getId());
        if (savedProduct == null) {
            throw new RuntimeException("Product not found with " + dto.getId());
        }
        boolean isChanged = false;
        if (dto.getPrice() != null) {
            savedProduct.setPrice(dto.getPrice());
            isChanged = true;
        }
        if (dto.getTitle() != null) {
            savedProduct.setTitle(dto.getTitle());
            isChanged = true;
        }
        if (dto.getAmount() != null) {
            savedProduct.setAmount(dto.getAmount());
            isChanged = true;
        }
        if (isChanged) {
            productRepository.save(savedProduct);
        }

    }

    @Override
    public ProductDTO getProduct(Long id) {
        return mapper.fromProduct(productRepository.getReferenceById(id));
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .title(productDTO.getTitle())
                .amount(productDTO.getAmount())
                .price(productDTO.getPrice())
                .imageurl(productDTO.getImageurl())
                .build();
        productRepository.save(product);
        return true;
    }

    @Override
    public List<ProductDTO> getAll() {
       // return mapper.fromProductList(productRepository.getAllByPriceNotNull());
        return mapper.fromProductList(productRepository.findAll());
    }

}

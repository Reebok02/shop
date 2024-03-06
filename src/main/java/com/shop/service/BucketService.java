package com.shop.service;

import com.shop.domain.Bucket;
import com.shop.domain.Product;
import com.shop.domain.User;
import com.shop.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);
    BucketDTO getBucketByUser(String name);
    void clearTheBucket(String name);
    void removeProductById(String name, Long id);


}

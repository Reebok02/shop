package com.shop.dao;

import com.shop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository <Bucket, Long> {

}

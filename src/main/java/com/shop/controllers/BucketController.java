package com.shop.controllers;

import com.shop.dao.BucketRepository;
import com.shop.dto.BucketDTO;
import com.shop.service.BucketService;
import com.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BucketController {
    private final BucketService bucketService;

    private final BucketRepository bucketRepository;

    public BucketController(BucketService bucketService, ProductService productService, BucketRepository bucketRepository) {
        this.bucketService = bucketService;
        this.bucketRepository = bucketRepository;
    }

    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        } else {
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }

    @GetMapping("/bucket/clear")
    public String clearBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        } else {
            bucketService.clearTheBucket(principal.getName());
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }

    @Transactional
    @PostMapping("/bucket/remove/{id}")
    public String removeProduct (Model model, Principal principal, @PathVariable("id") Long id) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        } else {
            bucketService.removeProductById(principal.getName(), id);
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }
        return "redirect:/bucket";
    }

}

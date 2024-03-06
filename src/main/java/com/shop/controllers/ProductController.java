package com.shop.controllers;

import com.shop.domain.Product;
import com.shop.dto.ProductDTO;
import com.shop.mapper.ProductMapper;
import com.shop.service.ProductService;
import com.shop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SessionObjectHolder sessionObjectHolder;

    public ProductController(ProductService productService, SessionObjectHolder sessionObjectHolder) {
        this.productService = productService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String list(Model model) {
        sessionObjectHolder.addClick();
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket (@PathVariable Long id, Principal principal) {
        sessionObjectHolder.addClick();
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }

    @PostMapping("/editProduct/{id}")
    public String editProducts(ProductDTO dto, Model model, @PathVariable("id") Long id) {
        if (id.equals(dto.getId())) {
            productService.editProduct(dto);
            model.addAttribute("product", dto);
            return "redirect:/products";
        }
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String productInfo(Model model,Principal principal, @PathVariable("id") Long id) {
        if (principal == null) {
            throw new RuntimeException("You are not authorize");
        }

        ProductDTO needDTO = new ProductDTO();
        List<ProductDTO> productDTOs = productService.getAll();
        for (ProductDTO dto : productDTOs) {
            if (dto.getId().equals(id)) {
                needDTO = dto;
            }
        }
        /*ProductMapper.MAPPER.toProduct(productService.getAll().get(Math.toIntExact(id)));*/
        /*Product product = ProductMapper.MAPPER.toProduct(needDTO);
        ProductDTO dto = ProductDTO.builder()
                .price(product.getPrice())
                .title(product.getTitle())
                .build();*/
        model.addAttribute("product", needDTO);
        return "editProduct";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "addNewProduct";
    }

    @PostMapping("/new")
    public String addProduct(Model model, ProductDTO productDTO) {
        if (productService.saveProduct(productDTO)) {
            return "redirect:/products";
        } else {
            model.addAttribute("product", productDTO);
            return "addNewProduct";
        }
    }

    @GetMapping("/cardProduct/{id}")
    public String cardProductInfo(Model model, @PathVariable("id") Long id) {
        ProductDTO needDTO = new ProductDTO();
        List<ProductDTO> productDTOs = productService.getAll();
        for (ProductDTO dto : productDTOs) {
            if (dto.getId().equals(id)) {
                needDTO = dto;
            }
        }
        model.addAttribute("product", ProductMapper.MAPPER.toProduct(needDTO));
        return "cardProduct";
    }
}

package com.space.datahub.controller;

import com.space.datahub.domain.Product;
import com.space.datahub.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductRepo productRepo;

    @Autowired
    public ProductController(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Product> list(){
        return productRepo.findAll();
    }

    @GetMapping("/filter/name")
    public Iterable<Product> byName(@RequestParam String name){
        Iterable<Product> products = null;
        if(name != null && !name.isEmpty()) {
            products = productRepo.findByName(name);
            return products;
        }
        return null;
    }

    @GetMapping("/filter/category/name")
    public Iterable<Product> byType(@RequestParam String category){
        Iterable<Product> products = null;
        if(category != null && !category.isEmpty()) {
            products = productRepo.findByCategoryName(category);
            return products;
        }
        return null;
    }

    @GetMapping("/filter/category/id")
    public Iterable<Product> byId(@RequestParam long id){
        Iterable<Product> products = null;
        products = productRepo.findByCategoryId(id);
        return products;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product){
        productRepo.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}

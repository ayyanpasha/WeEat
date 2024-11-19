package com.example.weeat.controller;

import com.example.weeat.entity.Product;
import com.example.weeat.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Create
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    //Read
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Integer id) {
        boolean isDeleted = productService.deleteProductById(id);
        return isDeleted ? ResponseEntity.ok("Product deleted successfully."):ResponseEntity.notFound().build();
    }
}

package com.example.weeat.service;

import com.example.weeat.entity.Product;
import com.example.weeat.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> getProductById(Integer id){
        return productRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Product> createProduct(Product product){
        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity<String> updateProduct(Integer id, Product product){
        if (product.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StringBuilder updatedFields = new StringBuilder("Updated Product id = " + id + ", ");

        if (product.getName() != null && !product.getName().equals(existingProduct.getName())) {
            existingProduct.setName(product.getName());
            updatedFields.append("name = ").append(product.getName()).append(", ");
        }
        if (product.getPrice() != null && !product.getPrice().equals(existingProduct.getPrice())) {
            existingProduct.setPrice(product.getPrice());
            updatedFields.append("price = ").append(product.getPrice()).append(", ");
        }

        productRepository.save(existingProduct);

        if (updatedFields.toString().endsWith(", ")) {
            updatedFields.setLength(updatedFields.length() - 2);
        }

        return ResponseEntity.ok(updatedFields.toString());
    }
}

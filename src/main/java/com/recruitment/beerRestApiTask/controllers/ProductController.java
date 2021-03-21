package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.Product;
import com.recruitment.beerRestApiTask.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
//@CrossOrigin(origins = {"http://localhost:4200"},
//        allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type"})
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getAllProducts(@PathVariable String id) {
        Optional<Product> product = this.productService.getProduct(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product,@PathVariable String id) {
        return this.productService.updateProduct(product, id);
    }
    @PostMapping
    @ResponseBody
    public Product saveProduct(@RequestBody Product product) {
        return this.productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
    }
}



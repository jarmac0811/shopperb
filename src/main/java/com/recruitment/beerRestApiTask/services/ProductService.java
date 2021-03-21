package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.Product;
import com.recruitment.beerRestApiTask.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository ProductRepository) {
        this.productRepository = ProductRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product Product) {
        return productRepository.save(Product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(Long.valueOf(id));
    }

    public Optional<Product> getProduct(String id) {
        Optional<Product> product = productRepository.findById(Long.valueOf(id));
        return product;
    }

    public Product updateProduct(Product product, String id) {
        Optional<Product> productFromDb = productRepository.findById(Long.valueOf(id));
        if (productFromDb.isPresent()) {
            Product product1 = productFromDb.get();
            product1.setTitle(product.getTitle());
            product1.setPrice(product.getPrice());
            product1.setCategory(product.getCategory());
            product1.setImageUrl(product.getImageUrl());
            return productRepository.save(product1);
        } else {
            return productRepository.save(product);
        }
    }
}

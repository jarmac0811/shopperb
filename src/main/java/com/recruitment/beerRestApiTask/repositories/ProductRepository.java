package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

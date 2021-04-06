package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.Product;
import com.recruitment.beerRestApiTask.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}

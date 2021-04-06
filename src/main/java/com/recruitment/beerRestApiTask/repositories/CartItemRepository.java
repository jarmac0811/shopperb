package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

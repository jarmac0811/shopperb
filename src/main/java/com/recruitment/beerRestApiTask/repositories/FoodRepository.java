package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    public Food findByName(String name);

    @Query("SELECT name FROM Food b WHERE b.name = ?1")
    List<Food> findByFoodPairingContaining(String foodPairing);
}

package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    Beer findByName(String name);

    Beer findByExternalId(long id);

    List<Beer> findByIdType(String userInput);

    @Query("SELECT b FROM Beer b join fetch Food f on b.id = f.beer WHERE f.name = ?1")
    List<Beer> findByFoodPairing(String foodPairing);

    List<Beer> findByPhonesLike(String phone);
}

package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findByUserId(String userId);
}

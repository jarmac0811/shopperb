package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}

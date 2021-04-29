package com.recruitment.beerRestApiTask.repositories;

import com.recruitment.beerRestApiTask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    User findByExternalUserId(String subject);

    User findByUserName(String userName);

    User findByEmail(String email);
}

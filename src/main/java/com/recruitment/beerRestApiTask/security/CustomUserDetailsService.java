package com.recruitment.beerRestApiTask.security;

import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return new UserPrincipal(user);
        } else {
            return new UserPrincipal();
        }
    }
}
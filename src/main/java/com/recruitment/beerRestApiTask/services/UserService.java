package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.domain.UserDTO;
import com.recruitment.beerRestApiTask.repositories.UserRepository;
import com.recruitment.beerRestApiTask.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUser(String id) {
        User user = userRepository.findByUserId(id);
        UserDTO userDTO = userMapper.map(user);
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : all) {
            dtoList.add(userMapper.map(user));
        }
        return dtoList;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        user = userRepository.save(user);
        return userMapper.map(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}

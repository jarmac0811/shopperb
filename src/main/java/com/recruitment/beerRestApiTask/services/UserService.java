package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.domain.UserDTO;
import com.recruitment.beerRestApiTask.exceptions.EmailExistsException;
import com.recruitment.beerRestApiTask.repositories.UserRepository;
import com.recruitment.beerRestApiTask.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO registerNewUser(UserDTO userDto) throws EmailExistsException {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + userDto.getEmail());
        }

                Double.valueOf("104308707124955157902");
        User user = new User();
        user.setExternalUserId(userDto.getExternalUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setRoles("USER");
        user.setAdmin(false);
        User u = userRepository.save(user);
        UserDTO userDTO = userMapper.map(u);
        return userDTO;
    }

    public UserDTO getUser(Long id) {
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

    public User findByExternalUserId(String userId) {
        return userRepository.findByExternalUserId(userId);
    }
}

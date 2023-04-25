package com.epam.userservice.service.impl;

import com.epam.userservice.entity.User;
import com.epam.userservice.repository.UserRepository;
import com.epam.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        log.info("Uploading user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isEmpty()) {
            return userRepository.save(user);
        }

        User userFromDb = userOptional.get();
        userFromDb.setUserName(user.getUserName());
        userFromDb.setAmountOfPosts(user.getAmountOfPosts());

        return userRepository.save(userFromDb);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Integer> deleteById(Integer id) {
        userRepository.deleteAllById(List.of(id));
        return List.of(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}

package com.epam.userservice.service;

import com.epam.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    User update(User user);

    Optional<User> getById(Integer id);

    List<Integer> deleteById(Integer id);

    List<User> getAll();
}


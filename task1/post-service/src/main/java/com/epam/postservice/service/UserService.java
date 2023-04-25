package com.epam.postservice.service;

import com.epam.postservice.entity.User;

public interface UserService {

    User getUser(Integer authorId);

    User updateUserData(User user);
}

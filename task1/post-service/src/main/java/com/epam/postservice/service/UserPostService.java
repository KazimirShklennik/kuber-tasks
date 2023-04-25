package com.epam.postservice.service;

import com.epam.postservice.entity.UserPost;

import java.util.List;
import java.util.Optional;

public interface UserPostService {

    UserPost save(UserPost userPost);

    UserPost update(UserPost userPost);

    Optional<UserPost> getById(Integer id);

    List<UserPost> getUserPostByAuthorId(Integer authorId);

    List<Integer> deleteById(Integer id);

    List<UserPost> getAll();
}


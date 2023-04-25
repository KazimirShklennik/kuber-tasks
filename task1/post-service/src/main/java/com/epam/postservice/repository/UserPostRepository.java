package com.epam.postservice.repository;

import com.epam.postservice.entity.UserPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepository extends CrudRepository<UserPost, Integer> {

    List<UserPost> getUserPostByAuthorId(Integer authorId);
}
package com.epam.postservice.service.impl;

import com.epam.postservice.entity.User;
import com.epam.postservice.entity.UserPost;
import com.epam.postservice.repository.UserPostRepository;
import com.epam.postservice.service.UserPostService;
import com.epam.postservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;
    private final UserService userService;

    @Override
    public UserPost save(UserPost userPost) {
        log.info("Uploading post: {}", userPost);
        userPost.setPostedAt(new Date());
        UserPost savedPost = userPostRepository.save(userPost);

        if (savedPost.getId() != null) {
            log.info("Saving user metadata post: {}", userPost);
            User user = userService.getUser(userPost.getAuthorId());

            if (user == null) {
                log.warn("Cannot find User: {}", userPost.getPostedAt());
                return null;
            }
            user.setAmountOfPosts(user.getAmountOfPosts() + 1);
            userService.updateUserData(user);
        }

        return savedPost;
    }

    @Override
    public UserPost update(UserPost userPost) {
        Optional<UserPost> userOptional = userPostRepository.findById(userPost.getId());

        if (userOptional.isEmpty()) {
            return this.save(userPost);
        }

        UserPost userPostFromDb = userOptional.get();
        userPostFromDb.setText(userPost.getText());
        userPostFromDb.setAuthorId(userPost.getAuthorId());

        return userPostRepository.save(userPostFromDb);
    }

    @Override
    public Optional<UserPost> getById(Integer id) {
        return userPostRepository.findById(id);
    }

    @Override
    public List<UserPost> getUserPostByAuthorId(Integer authorId) {
        return userPostRepository.getUserPostByAuthorId(authorId);
    }

    @Override
    public List<Integer> deleteById(Integer id) {
        userPostRepository.deleteAllById(List.of(id));
        return List.of(id);
    }

    @Override
    public List<UserPost> getAll() {
        return (List<UserPost>) userPostRepository.findAll();
    }
}

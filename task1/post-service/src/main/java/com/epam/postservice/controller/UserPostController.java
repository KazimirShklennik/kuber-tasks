package com.epam.postservice.controller;

import com.epam.postservice.entity.UserPost;
import com.epam.postservice.service.UserPostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/post-service/posts")
public class UserPostController {

    private final UserPostService userPostService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new post was created"),
            @ApiResponse(code = 400, message = "Song metadata missing validation error"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Create a new post record in database")
    @PostMapping
    public UserPost save(@ApiParam(value = "User post model", required = true)
                         @RequestBody UserPost userPost) {
        return userPostService.save(userPost);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new post was created"),
            @ApiResponse(code = 400, message = "Song metadata missing validation error"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Create a new post record in database")
    @PutMapping
    public UserPost updatePost(@ApiParam(value = "User Post moel", required = true)
                               @RequestBody UserPost userPost) {
        return userPostService.update(userPost);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns posts"),
            @ApiResponse(code = 404, message = "The user metadata with the specified resourceKey does not exist"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Get poata")
    @GetMapping
    public List<UserPost> getAllPosts() {
        return userPostService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a post by id"),
            @ApiResponse(code = 404, message = "The post metadata with the specified resourceKey does not exist"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Get post by id")
    @GetMapping("/{id}")
    public UserPost getPostById(@ApiParam(value = "Post metadata id to get", required = true)
                                @PathVariable("id") Integer id) {
        return userPostService.getById(id).orElse(null);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a post by post id"),
            @ApiResponse(code = 404, message = "The post metadata with the specified resourceKey does not exist"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Get post by author id")
    @GetMapping("/author/{authorId}")
    public List<UserPost> getPostsByAuthorId(@ApiParam(value = "Posts metadata authorId to get", required = true)
                                             @PathVariable("authorId") Integer authorId) {
        return userPostService.getUserPostByAuthorId(authorId);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post was deleted"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Delete a pots(s) metadata. If there is no user metadata for id, do nothing")
    @DeleteMapping
    public List<Integer> deleteSong(@ApiParam(value = "Post ID to remove", required = true)
                                    @RequestParam("id") Integer id) {
        return userPostService.deleteById(id);
    }
}
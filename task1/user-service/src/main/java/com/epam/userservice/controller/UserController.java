package com.epam.userservice.controller;

import com.epam.userservice.entity.User;
import com.epam.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user-service/users")
public class UserController {

    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new user was created"),
            @ApiResponse(code = 400, message = "Song metadata missing validation error"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Create a new user record in database")
    @PostMapping
    public User save(@ApiParam(value = "User", required = true)
                     @RequestBody User user) {
        return userService.save(user);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new user was created"),
            @ApiResponse(code = 400, message = "Song metadata missing validation error"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Create a new user record in database")
    @PutMapping
    public User updateUser(@ApiParam(value = "User", required = true)
                           @RequestBody User user) {
        return userService.update(user);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns users"),
            @ApiResponse(code = 404, message = "The user metadata with the specified resourceKey does not exist"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Get users")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a user by id"),
            @ApiResponse(code = 404, message = "The user metadata with the specified resourceKey does not exist"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    public User getUserById(@ApiParam(value = "Song metadata resourceKey to get", required = true)
                            @PathVariable("id") Integer id) {
        return userService.getById(id).orElse(null);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was deleted"),
            @ApiResponse(code = 500, message = "An internal server error has occurred")})
    @ApiOperation(value = "Delete a user(s) metadata. If there is no user metadata for id, do nothing")
    @DeleteMapping
    public List<Integer> deleteSong(@ApiParam(value = "User ID to remove", required = true)
                                    @RequestParam("id") Integer id) {
        return userService.deleteById(id);
    }
}
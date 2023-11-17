package tasks.webapplication.controllers;

import entities.UserRegistrationRequest;
import org.springframework.web.bind.annotation.*;
import tasks.webapplication.services.UserService;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationRequest user) {
        return userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
    }




}

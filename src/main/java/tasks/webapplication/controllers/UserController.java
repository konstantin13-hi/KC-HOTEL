package tasks.webapplication.controllers;

import entities.UserLoginRequest;
import entities.UserProfileResponse;
import entities.UserRegistrationRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.webapplication.services.UserService;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

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

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
//        return userService.loginUser(userLoginRequest.getEmail(),userLoginRequest.getPassword());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        return userService.loginUser(userLoginRequest.getEmail(), userLoginRequest.getPassword(), response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@CookieValue(value = "token", required = false) String token) {
        return userService.getProfile(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletResponse response) {
        return ResponseEntity.ok(userService.logout(response));
    }






}

//package webapplication.controllers;
//
//import dto.UserLoginRequest;
//import dto.UserProfileResponse;
//import dto.UserRegistrationRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import webapplication.services.UserService;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//
//@RestController
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest user) {
//        try {
//            userService.registerUser(user);
//            return ResponseEntity.ok("Registration successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
//        return userService.loginUser(userLoginRequest.getEmail(), userLoginRequest.getPassword(), response);
//    }
//
//    @GetMapping("/profile")
//    public ResponseEntity<UserProfileResponse> getProfile(@CookieValue(value = "token", required = false) String token) {
//        return userService.getProfile(token);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<Boolean> logout(HttpServletResponse response) {
//        return ResponseEntity.ok(userService.logout(response));
//    }
//
//

//
//
//
//
//
//
//
//}

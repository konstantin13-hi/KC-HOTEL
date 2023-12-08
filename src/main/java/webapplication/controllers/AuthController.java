package webapplication.controllers;

import dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import webapplication.Utils.JwtTokenUtils;
import webapplication.services.AuthService;
import webapplication.services.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;


//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
//     return authService.createAuthToken(authRequest);
//

      @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return authService.loginUser(userLoginRequest);
    }
//    @PostMapping("/registration")
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
     return  authService.createNewUser(registrationUserDto);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

          return userService.getProfile(authorizationHeader);
    }

    @PostMapping("/logOut")
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(){
        return "secured part of web service";
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

//    @GetMapping ("/login")
//    public String loginPage(){
//        return "hi";
//    }





}

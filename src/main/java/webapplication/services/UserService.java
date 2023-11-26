package webapplication.services;

import webapplication.dto.UserLoginRequest;
import webapplication.dto.UserProfileResponse;
import webapplication.dto.UserRegistrationRequest;
import webapplication.dto.UserResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webapplication.JwtTokenProvider;
import webapplication.repositories.UserRepository;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        Optional<User> existingUser = userRepository.findByEmail(userRegistrationRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = new User();
        user.setName(userRegistrationRequest.getName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        userRepository.save(user);
    }


public ResponseEntity<UserResponse> loginUser(UserLoginRequest userLoginRequest,HttpServletResponse response) {

    Optional<User> userOptional = userRepository.findByEmail(userLoginRequest.getEmail());

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            String token = tokenProvider.generateToken(user.getEmail(),user.getId());

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            return ResponseEntity.ok(userResponse);
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(null);
}

    public ResponseEntity<UserProfileResponse> getProfile(String token) {
        if (token != null) {
            try {
                String email = tokenProvider.extractEmailFromToken(token);
                Optional<User> userOptional = userRepository.findByEmail(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    UserProfileResponse userProfile = new UserProfileResponse(user.getName(), user.getEmail());
                    return ResponseEntity.ok(userProfile);
                }
            } catch (JwtException e) {
                return ResponseEntity.ok(null);
            }
        }

        return ResponseEntity.ok(null);
    }


    public boolean logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return true;
    }






}

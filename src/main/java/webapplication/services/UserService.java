package webapplication.services;

import dto.UserProfileResponse;
import dto.UserRegistrationRequest;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import webapplication.JwtTokenProvider;
import webapplication.configs.UserRole;
import webapplication.repositories.UserRepository;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;


@Service
public class UserService   {
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
        user.setRoles(Collections.singleton(UserRole.ROLE_USER));
        userRepository.save(user);
    }


public ResponseEntity<?> loginUser(String email, String password, HttpServletResponse response) {
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = tokenProvider.generateToken(user.getEmail(),user.getId());

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return ResponseEntity.ok(user);
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid credentials");
}

    public ResponseEntity<UserProfileResponse> getProfile(String token) {
        if (token != null) {
            try {
                String email = tokenProvider.extractEmailFromToken(token);
                Optional<User> userOptional = userRepository.findByEmail(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    UserProfileResponse userProfile = new UserProfileResponse(user.getName(), user.getEmail(), user.getId());
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

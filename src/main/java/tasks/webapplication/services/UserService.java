package tasks.webapplication.services;

import entities.UserProfileResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tasks.webapplication.JwtTokenProvider;
import tasks.webapplication.repositories.UserRepository;
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

    public User registerUser(String name, String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);

    }

//    public ResponseEntity<?> loginUser(String email, String password) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                String token = tokenProvider.generateToken(user.getEmail());
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                        .body(user);
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body("Invalid credentials");
//    }
public ResponseEntity<?> loginUser(String email, String password, HttpServletResponse response) {
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = tokenProvider.generateToken(user.getEmail());

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
                // Обработка ошибки верификации токена
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        // Если токен отсутствует или произошла ошибка, возвращаем null
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

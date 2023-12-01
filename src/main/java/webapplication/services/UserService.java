package webapplication.services;

import dto.RegistrationUserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import webapplication.repositories.UserRepository;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.repositories.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static entities.UserRoleName.ROLE_USER;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(findByName(name).orElseThrow(() -> new UsernameNotFoundException(String.format("not found user", name))));
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList())

        );
    }
    public void createNewUser(RegistrationUserDto registrationUserDto){
        User user = new User();
        user.setName(registrationUserDto.getName());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(Set.of(userRoleRepository.findByName(ROLE_USER).get()));
        userRepository.save(user);
    }

//    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
//        Optional<User> existingUser = userRepository.findByEmail(userRegistrationRequest.getEmail());
//        if (existingUser.isPresent()) {
//            throw new RuntimeException("User with this email already exists");
//        }
//
//        // Проверяем существующую роль "ROLE_USER"
//        UserRole userRole = userRoleRepository.findByName(UserRoleName.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Default role not found"));
//
//        User user = new User();
//        user.setName(userRegistrationRequest.getName());
//        user.setEmail(userRegistrationRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
//
//        // Устанавливаем роли для пользователя
//        user.setRoles(Collections.singleton(userRole));
//        userRepository.save(user);
//    }
//
//
////public ResponseEntity<?> loginUser(String email, String password, HttpServletResponse response) {
////        loadUserByUsername(email);
////    Optional<User> userOptional = userRepository.findByEmail(email);
////
////    if (userOptional.isPresent()) {
////        User user = userOptional.get();
////        if (passwordEncoder.matches(password, user.getPassword())) {
////            String token = tokenProvider.generateToken(user.getEmail(),user.getId());
////
////            Cookie cookie = new Cookie("token", token);
////            cookie.setHttpOnly(true);
////            response.addCookie(cookie);
////
////            return ResponseEntity.ok(user);
////        }
////    }
////
////    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
////            .body("Invalid credentials");
////}
//
//    public class AuthResponse {
//        private String username;
//
//        public AuthResponse(String username) {
//            this.username = username;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//    }
//
//    // Ваш метод loginUser
//    public ResponseEntity<?> loginUser(String email, String password, HttpServletResponse response) {
//        UserDetails userDetails;
//        try {
//            userDetails = loadUserByUsername(email);
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Invalid credentials");
//        }
//
//        if (passwordEncoder.matches(password, userDetails.getPassword())) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = tokenProvider.generateToken(email, ((User) userDetails).getId());
//
//            Cookie cookie = new Cookie("token", token);
//            cookie.setHttpOnly(true);
//            response.addCookie(cookie);
//
//            // Создаем объект AuthResponse с данными пользователя и токеном
//            AuthResponse authResponse = new AuthResponse(userDetails.getUsername());
//
//            // Возвращаем объект AuthResponse вместо строки
//            return ResponseEntity.ok(userDetails);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body("Invalid credentials");
//    }
//
//    public ResponseEntity<UserProfileResponse> getProfile(String token) {
//        if (token != null) {
//            try {
//                String email = tokenProvider.extractEmailFromToken(token);
//                Optional<User> userOptional = userRepository.findByEmail(email);
//
//                if (userOptional.isPresent()) {
//                    User user = userOptional.get();
//                    UserProfileResponse userProfile = new UserProfileResponse(user.getName(), user.getEmail(), user.getId());
//                    return ResponseEntity.ok(userProfile);
//                }
//            } catch (JwtException e) {
//                return ResponseEntity.ok(null);
//            }
//        }
//
//        return ResponseEntity.ok(null);
//    }
//
//
//    public boolean logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("token", null);
//        cookie.setMaxAge(0);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return true;
//    }
//
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////
////        Optional<User> userEntityOptional = userRepository.findByEmail(username);
////        if (userEntityOptional.isPresent()) {
////            return userEntityOptional.get();
////        } else {
////            throw new UsernameNotFoundException("User not found with email: " + username);
////        }
////    }
//
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Найдите пользователя в базе данных по его email (или любому другому уникальному полю)
//        Optional<User> userOptional = userRepository.findByEmail(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            // Создайте объект UserDetails, используя данные пользователя из базы данных
//            List<GrantedAuthority> authorities = user.getRoles().stream()
//                    .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
//                    .collect(Collectors.toList());
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getEmail(),
//                    user.getPassword(),
//                    authorities
//            );
//        } else {
//            throw new UsernameNotFoundException("User not found with email: " + username);
//        }
//    }

}

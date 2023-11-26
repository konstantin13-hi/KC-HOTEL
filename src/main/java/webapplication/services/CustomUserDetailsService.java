package webapplication.services;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webapplication.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

//@Service
//public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return new org.springframework.security.core.userdetails.User(
//                    user.getEmail(),
//                    user.getPassword(),
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//        } else {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//    }
//}
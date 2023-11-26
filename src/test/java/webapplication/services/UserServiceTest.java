package webapplication.services;

import entities.User;
import org.junit.jupiter.api.Test;

import webapplication.dto.UserRegistrationRequest;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import webapplication.JwtTokenProvider;
import webapplication.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class UserServiceTest {



    @Test
    public void testRegisterUser() {

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
        UserService userService = new UserService(userRepository,bCryptPasswordEncoder,jwtTokenProvider);
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("John Doe", "john@example.com", "password123");

        when(userRepository.findByEmail(userRegistrationRequest.getEmail())).thenReturn(Optional.empty());

        when(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword())).thenReturn("encodedPassword");


        userService.registerUser(userRegistrationRequest);

        verify(userRepository, times(1)).findByEmail(userRegistrationRequest.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder, times(1)).encode(userRegistrationRequest.getPassword());
    }


}
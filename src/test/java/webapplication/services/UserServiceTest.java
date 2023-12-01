package webapplication.services;

import entities.User;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webapplication.JwtTokenProvider;
import webapplication.controllers.UserController;
import webapplication.repositories.UserRepository;
import webapplication.services.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserServiceTest {



//    @Test
//    public void testRegisterUser() {
//
//        UserRepository userRepository = Mockito.mock(UserRepository.class);
//        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
//        JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
//        UserService userService = new UserService(userRepository,bCryptPasswordEncoder,jwtTokenProvider);
//        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("John Doe", "john@example.com", "password123");
//
//        when(userRepository.findByEmail(userRegistrationRequest.getEmail())).thenReturn(Optional.empty());
//
//        when(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword())).thenReturn("encodedPassword");
//
//
//        userService.registerUser(userRegistrationRequest);
//
//        verify(userRepository, times(1)).findByEmail(userRegistrationRequest.getEmail());
//        verify(userRepository, times(1)).save(any(User.class));
//        verify(bCryptPasswordEncoder, times(1)).encode(userRegistrationRequest.getPassword());
//    }


}
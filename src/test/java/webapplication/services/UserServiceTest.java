package webapplication.services;

import entities.User;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import webapplication.controllers.UserController;
import webapplication.dto.UserLoginRequest;
import webapplication.dto.UserProfileResponse;
import webapplication.dto.UserRegistrationRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import webapplication.JwtTokenProvider;
import webapplication.dto.UserResponse;
import webapplication.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<Cookie> cookieCaptor;


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

    @Nested
    public class LoginUser{


            @Test
            public void testLoginUser_SuccessfulLogin() {
                UserLoginRequest userLoginRequest = new UserLoginRequest("test@example.com", "password");
                User user = new User(1L,"name","test@example.com", "hashedPassword");
                String token = "testToken";
//                UserRepository userRepository = Mockito.mock(UserRepository.class);
//                BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
//                JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
//                UserService userService = new UserService(userRepository,bCryptPasswordEncoder,jwtTokenProvider);
                when(userRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.of(user));
                when(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
                when(jwtTokenProvider.generateToken(user.getEmail(), user.getId())).thenReturn(token);
                HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
                ResponseEntity<UserResponse> responseEntity = userService.loginUser(userLoginRequest, mockResponse);
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                System.out.println(mockResponse.toString());
                UserResponse userResponse = responseEntity.getBody();
                assertEquals(user.getName(), userResponse.getName());
                assertEquals(user.getEmail(), userResponse.getEmail());
            }



    }

    @Nested
    public class getProfile{
//        UserRepository userRepository = Mockito.mock(UserRepository.class);
//        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
//        JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
//        UserService userService = new UserService(userRepository,bCryptPasswordEncoder,jwtTokenProvider);

        @Test
        public void testGetProfile_ValidToken() {
            String validToken = "validToken";
            String userEmail = "test@example.com";
            User user = new User(1L,"Test User", userEmail,"hashedpass");

            when(jwtTokenProvider.extractEmailFromToken(validToken)).thenReturn(userEmail);
            when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));

            ResponseEntity<UserProfileResponse> responseEntity = userService.getProfile(validToken);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

            UserProfileResponse userProfileResponse = responseEntity.getBody();
            assertEquals(user.getName(), userProfileResponse.getName());
            assertEquals(user.getEmail(), userProfileResponse.getEmail());
        }

        @Test
        public void testGetProfile_InvalidToken() {
            String invalidToken = "invalidToken";

            when(jwtTokenProvider.extractEmailFromToken(invalidToken)).thenThrow(JwtException.class);

            ResponseEntity<UserProfileResponse> responseEntity = userService.getProfile(invalidToken);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

            assertEquals(null, responseEntity.getBody());
        }

        @Test
        public void testGetProfile_UserNotFound() {
            String validToken = "validToken";
            String userEmail = "nonexistent@example.com";

            when(jwtTokenProvider.extractEmailFromToken(validToken)).thenReturn(userEmail);
            when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());

            ResponseEntity<UserProfileResponse> responseEntity = userService.getProfile(validToken);


            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(null, responseEntity.getBody());
        }

    }
    @Nested
    public class LogOut{
//        UserRepository userRepository = Mockito.mock(UserRepository.class);
//        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
//        JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);
//        UserService userService = new UserService(userRepository,bCryptPasswordEncoder,jwtTokenProvider);

        @Test
        public void testLogout() {

            HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);


            boolean result = userService.logout(mockResponse);

            assertEquals(true, result);

            Mockito.verify(mockResponse, times(1)).addCookie(Mockito.argThat(cookie ->
                    "token".equals(cookie.getName()) &&
                            null == cookie.getValue() &&
                            0 == cookie.getMaxAge() &&
                            true == cookie.isHttpOnly() &&
                            "/".equals(cookie.getPath())
            ));

        }
    }


}
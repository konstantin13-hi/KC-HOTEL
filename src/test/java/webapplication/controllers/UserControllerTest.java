package webapplication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webapplication.dto.UserLoginRequest;
import webapplication.dto.UserProfileResponse;
import webapplication.dto.UserRegistrationRequest;
import webapplication.dto.UserResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import webapplication.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Nested
    public class RegisterUser{
        @Test
        public void registerUser() throws Exception {
            UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("1","2@gmail.com","33333333");
            final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/register")
                    .content(objectMapper.writeValueAsString(userRegistrationRequest))
                    .contentType(MediaType.APPLICATION_JSON);
            final var result = mockMvc.perform(requestBuilder).andExpect(status().isOk());

            verify(userService).registerUser(userRegistrationRequest);
        }
    }

    @Nested
    public class LoginUser{
        @Test
        public void testLoginUser_SuccessfulLogin() throws Exception {
            UserLoginRequest userLoginRequest = new UserLoginRequest( "password","test@example.com");
            UserResponse expectedUserResponse = new UserResponse("password","test@example.com");
              final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/login")
                    .content(objectMapper.writeValueAsString(userLoginRequest))
                    .contentType(MediaType.APPLICATION_JSON);
            final var result = mockMvc.perform(requestBuilder).andExpect(status().isOk());
            Mockito.when(userService.loginUser(Mockito.any(UserLoginRequest.class), Mockito.any(HttpServletResponse.class)))
                    .thenReturn(ResponseEntity.ok().body(expectedUserResponse));




        }

    }

    @Nested
    public class GetProfile{
        @Test
        public void testGetProfile() throws Exception {
            String token = "testToken";
            UserProfileResponse userProfileResponse = new UserProfileResponse("testUser", "test@example.com");
            Mockito.when(userService.getProfile(Mockito.anyString()))
                    .thenReturn(ResponseEntity.ok(userProfileResponse));
            final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/profile")
                    .cookie(new Cookie("token", token));
            mockMvc.perform(requestBuilder).andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.content().
                                    string(objectMapper.writeValueAsString(userProfileResponse)

            ));



        }

    }
    @Nested
    public class LogOut{
        @Test
        public void testLogout() throws Exception {
            Mockito.when(userService.logout(Mockito.any(HttpServletResponse.class)))
                    .thenReturn(true);
            final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/logout");
            mockMvc.perform(requestBuilder).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().
                            string("true"));
        }
        }
}
//package webapplication.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dto.UserLoginRequest;
//import dto.UserRegistrationRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import webapplication.services.UserService;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//
//@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc
//class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void registerUser() throws Exception {
//        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("1","2@gmail.com","33333333");
//        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/register")
//                .content(objectMapper.writeValueAsString(userRegistrationRequest))
//                .contentType(MediaType.APPLICATION_JSON);
//      final var result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
//
//
//        verify(userService).registerUser(userRegistrationRequest);
//    }
//
//
////    @Test
////    public void loginUser() throws Exception {
////        // Создаем экземпляр UserLoginRequest для использования в запросе
////        UserLoginRequest userLoginRequest = new UserLoginRequest("test@example.com", "password");
////
////        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("/login")
////                .content(objectMapper.writeValueAsString(userLoginRequest))
////                .contentType(MediaType.APPLICATION_JSON);
////
////
////        final var result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
////
////
////        verify(userService).loginUser(eq(userLoginRequest.getEmail()), eq(userLoginRequest.getPassword()), any());
////
////        // Используем ArgumentCaptor для захвата параметров, переданных в userService.loginUser
////        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
////        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
////
////        verify(userService).loginUser(emailCaptor.capture(), passwordCaptor.capture(), any());
////
////
////                Assertions.assertEquals("test@example.com", emailCaptor.getValue());
////                Assertions.assertEquals("password", passwordCaptor.getValue());
////
////    }
//
//
//}
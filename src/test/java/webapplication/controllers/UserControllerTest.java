package webapplication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webapplication.services.UserService;

import static org.mockito.Mockito.verify;
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

    @Test
    public void registerUser() throws Exception {
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("1","2","3");
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .content(objectMapper.writeValueAsString(userRegistrationRequest))
                .contentType(MediaType.APPLICATION_JSON);
      final var result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

        verify(userService).registerUser(userRegistrationRequest);
    }


}
package webapplication.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Place;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webapplication.dto.PlaceCreateRequest;
import webapplication.dto.PlaceRequest;
import webapplication.dto.UserPlacesResponse;
import webapplication.services.PlaceService;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceController.class)
@AutoConfigureMockMvc
public class PlaceControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlaceService placeService;


    @Nested
    public class TTT {

        @Test
        public void test1() throws Exception {
            PlaceCreateRequest placeCreateRequest = new PlaceCreateRequest();
            String token = "testToken";
            placeCreateRequest.setTitle("Sample Title");
            placeCreateRequest.setAddress("Sample Address");
            placeCreateRequest.setAddedPhotos(Arrays.asList("photo1.jpg", "photo2.jpg"));
            placeCreateRequest.setDescription("Sample Description");
            placeCreateRequest.setPerks(Arrays.asList("Perk 1", "Perk 2"));
            placeCreateRequest.setExtraInfo("Sample Extra Info");
            placeCreateRequest.setCheckIn(14);
            placeCreateRequest.setCheckOut(11);
            placeCreateRequest.setMaxGuests(4);
            placeCreateRequest.setPrice(100.0);
            ResponseEntity<String> responseEntity = ResponseEntity.ok("CREATED");
            when(placeService.createPlace(placeCreateRequest, token)).thenReturn(responseEntity);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/places")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(placeCreateRequest))
                    .cookie(new Cookie("token", token));
            ;

            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("CREATED"));
        }
    }

    @Nested
    public class GetUserPlaces {
        @Test
        public void testUserPlaces() throws Exception {
            String token = "token";
            List<UserPlacesResponse> userPlacesResponses = Arrays.asList(
                    new UserPlacesResponse(1L),
                    new UserPlacesResponse(2L));
            ResponseEntity<List<UserPlacesResponse>> responseEntity = ResponseEntity.ok(userPlacesResponses);
            when(placeService.getUserPlaces(token)).thenReturn(responseEntity);

            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/user-places")
                    .cookie(new Cookie("token", token));
           final var result = mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(userPlacesResponses)));
            verify(placeService).getUserPlaces(token);
        }
    }

//    @Nested
//    public class UpdatePlace{
//        @Test
//        public void testUpdatePlace() throws Exception {
//            Long placeId = 1L;
//            PlaceRequest placeRequest = new PlaceRequest();
//            String token = "StringToken";
//            placeRequest.setAddress("New Address");
//            ResponseEntity<String> responseEntity = ResponseEntity.ok("123");
//            when(placeService.updatePlace(placeId, placeRequest, token)).thenReturn(responseEntity);
//                    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                    .put("/places/{id}", placeId)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(placeRequest))
//                    .cookie(new Cookie("token", token));
//            mockMvc.perform(requestBuilder)
//                    .andExpect(status().isOk())
//                    .andExpect(MockMvcResultMatchers.content().string("Place updated successfully"))
//                  ;
//            verify(placeService).updatePlace(placeId, placeRequest, token);
//        }
//
//    }

    @Nested
    public class GetAllPlaces{
        @Test
        void testGetAllPlaces() throws Exception {
            // Assuming you have a list of places for testing
            List<Place> mockPlaces = Arrays.asList(
                    new Place(1L, "Place 1", "Address 1"),
                    new Place(2L, "Place 2", "Address 2")
                    // Add more places as needed
            );


            when(placeService.getAllPlaces()).thenReturn(ResponseEntity.ok(mockPlaces));

            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/places")
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(mockPlaces)));

            verify(placeService, times(1)).getAllPlaces();
        }
    }
}

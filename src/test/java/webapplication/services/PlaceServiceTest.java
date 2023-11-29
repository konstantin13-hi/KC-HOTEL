package webapplication.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webapplication.JwtTokenProvider;
import webapplication.controllers.PlaceController;
import webapplication.dto.PlaceCreateRequest;
import webapplication.repositories.PlaceRepository;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {



    @Mock
    private PlaceRepository userRepository;


    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private PlaceService placeService;



//    @Test
//    public void testCreatePlace() throws Exception {
//        // Create a sample request object
//        PlaceCreateRequest placeCreateRequest = new PlaceCreateRequest();
//        placeCreateRequest.setTitle("Sample Title");
//        placeCreateRequest.setAddress("Sample Address");
//        placeCreateRequest.setAddedPhotos(Arrays.asList("photo1.jpg", "photo2.jpg"));
//        placeCreateRequest.setDescription("Sample Description");
//        placeCreateRequest.setPerks(Arrays.asList("Perk 1", "Perk 2"));
//        placeCreateRequest.setExtraInfo("Sample Extra Info");
//        placeCreateRequest.setCheckIn(14);
//        placeCreateRequest.setCheckOut(11);
//        placeCreateRequest.setMaxGuests(4);
//        placeCreateRequest.setPrice(100.0);
//
//        // Mock the behavior of the placeService.createPlace method
//        when(placeService.createPlace(placeCreateRequest, "token")).thenReturn(ResponseEntity.ok("CREATED"));
//
//
//        // Verify that placeService.createPlace was called with the correct parameters
//        verify(placeServ).createPlace(placeCreateRequest, "token");
//    }
//
//
//    public void testGetUserPlaces() throws Exception {
//        List<UserPlacesResponse> userPlacesResponses = new ArrayList<>();
//        ResponseEntity<List<UserPlacesResponse>> responseEntity = ResponseEntity.ok(userPlacesResponses);
//        when(placeService.getUserPlaces("token")).thenReturn(responseEntity);
//
//        // Выполняем GET-запрос на /user-places
//        mockMvc.perform(get("/user-places")
//                        .cookie(new Cookie("token", "token")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray());
//
//
//        verify(placeService).getUserPlaces("token");
//    }



}
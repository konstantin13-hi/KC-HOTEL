package tasks.webapplication.controllers;

import entities.Place;
import entities.PlaceCreateRequest;
import entities.User;
import entities.UserPlacesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tasks.webapplication.JwtTokenProvider;
import tasks.webapplication.repositories.PlaceRepository;
import tasks.webapplication.services.PlaceService;

import java.util.List;

@RestController
//@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;


    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;

    }


    @PostMapping("/places")
    public ResponseEntity<Place> createPlace(@RequestBody PlaceCreateRequest placeCreateRequest, @CookieValue(name = "token") String token) {
        return placeService.createPlace(placeCreateRequest, token);
    }


    @GetMapping("/user-places")
    public ResponseEntity<List<UserPlacesResponse>> getUserPlaces(@CookieValue(name = "token") String token) {
        return placeService.getUserPlaces(token);
    }


    @GetMapping("/places/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
       return placeService.getPlaceById(id);
    }
}



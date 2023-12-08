package webapplication.controllers;

import dto.PlaceCreateRequest;
import dto.PlaceRequest;
import dto.UserPlacesResponse;
import entities.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import webapplication.services.PlaceService;

import java.util.List;

@RestController
//@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;


    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;

    }


//    @PostMapping("/places")
//    public ResponseEntity<Place> createPlace(@RequestBody PlaceCreateRequest placeCreateRequest, @CookieValue(name = "token") String token) {
//        return placeService.createPlace(placeCreateRequest, token);
//    }
    @PostMapping("/places")
    public ResponseEntity<?> createPlace(@RequestBody PlaceCreateRequest placeCreateRequest, @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

        return placeService.createPlace(placeCreateRequest, authorizationHeader);
    }


    @GetMapping("/user-places")
    public ResponseEntity<List<UserPlacesResponse>> getUserPlaces(@RequestHeader(name = "Authorization" , required = false) String authorizationHeader) {
        return placeService.getUserPlaces(authorizationHeader);
    }


    @GetMapping("/places/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        System.out.println(id);
       return placeService.getPlaceById(id);
    }


    @PutMapping("/places/{id}")
    public ResponseEntity<String> updatePlace(@PathVariable Long id, @RequestBody PlaceRequest placeRequest, @RequestHeader(name = "Authorization" , required = false) String authorizationHeader) {
        System.out.println(placeRequest.toString());
        return placeService.updatePlace(id,placeRequest,authorizationHeader);
    }


    @GetMapping("/places")
    public ResponseEntity<List<Place>> getAllPlaces() {
       return placeService.getAllPlaces();
    }


}



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


    @PutMapping("/places/{id}")
    public ResponseEntity<String> updatePlace(@PathVariable Long id, @RequestBody PlaceRequest placeRequest, @CookieValue(name = "token") String token) {
        System.out.println(placeRequest.toString());
        return placeService.updatePlace(id,placeRequest,token);
    }


    @GetMapping("/places")
    public ResponseEntity<List<Place>> getAllPlaces() {
       return placeService.getAllPlaces();
    }


}



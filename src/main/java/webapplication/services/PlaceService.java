package webapplication.services;

import webapplication.dto.PlaceCreateRequest;
import webapplication.dto.PlaceRequest;
import webapplication.dto.UserPlacesResponse;
import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import webapplication.JwtTokenProvider;
import webapplication.repositories.PlaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final JwtTokenProvider jwtService;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, JwtTokenProvider jwtService) {
        this.placeRepository = placeRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> createPlace(PlaceCreateRequest placeCreateRequest, String token) {
        try {
            String userId = jwtService.extractIdFromToken(token);
            Long userIdLong = Long.parseLong(userId);

            Place place = new Place();
            User owner = new User();
            owner.setId(userIdLong);
            place.setOwnerId(owner);
            place.setTitle(placeCreateRequest.getTitle());
            place.setAddress(placeCreateRequest.getAddress());
            place.setPhotos(placeCreateRequest.getAddedPhotos());
            place.setDescription(placeCreateRequest.getDescription());
            place.setPerks(placeCreateRequest.getPerks());
            place.setExtraInfo(placeCreateRequest.getExtraInfo());
            place.setCheckIn(placeCreateRequest.getCheckIn());
            place.setCheckOut(placeCreateRequest.getCheckOut());
            place.setMaxGuests(placeCreateRequest.getMaxGuests());
            place.setPrice(placeCreateRequest.getPrice());

            Place createdPlace = placeRepository.save(place);

            return ResponseEntity.ok("CREATED");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<List<UserPlacesResponse>> getUserPlaces(@CookieValue(name = "token") String token) {
        try {
            Long ownerId = Long.parseLong(jwtService.extractIdFromToken(token));
            User owner = new User();
            owner.setId(ownerId);
            List<Place> places = placeRepository.findByOwnerId(owner);
            return ResponseEntity.ok(mapPlacesToUserPlacesResponse(places));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<UserPlacesResponse> mapPlacesToUserPlacesResponse(List<Place> places) {
        return places.stream()
                .map(place -> new UserPlacesResponse(
                        place.getId(),
                        place.getTitle(),
                        place.getAddress(),
                        place.getPhotos(),
                        place.getDescription(),
                        place.getPerks(),
                        place.getExtraInfo(),
                        place.getCheckIn(),
                        place.getCheckOut(),
                        place.getMaxGuests(),
                        place.getPrice()
                ))
                .collect(Collectors.toList());
    }



    public ResponseEntity<Place> getPlaceById(Long id) {
        Optional<Place> place = placeRepository.findById(id);
//        System.out.println(id);
//        place.ifPresent(value -> System.out.println(value.toString()));
        return place.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    public ResponseEntity<String> updatePlace(Long id, PlaceRequest placeRequest, String token) {
        try {
            Long ownerId = Long.parseLong(jwtService.extractIdFromToken(token));
            Optional<Place> optionalPlace = placeRepository.findById(id);

            if (optionalPlace.isPresent()) {
                Place place = optionalPlace.get();


                if (place.getOwnerId().getId().equals(ownerId)) {
                    place.setTitle(placeRequest.getTitle());
                    place.setAddress(placeRequest.getAddress());
                    place.setPhotos(placeRequest.getPhotos());
                    place.setDescription(placeRequest.getDescription());
                    place.setPerks(placeRequest.getPerks());
                    place.setExtraInfo(placeRequest.getExtraInfo());
                    place.setCheckIn(placeRequest.getCheckIn());
                    place.setCheckOut(placeRequest.getCheckOut());
                    place.setMaxGuests(placeRequest.getMaxGuests());
                    place.setPrice(placeRequest.getPrice());
                    placeRepository.save(place);

                    return ResponseEntity.ok("Place updated successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to update this place");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Place not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating place");
        }
    }


    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return ResponseEntity.ok(places);
    }

}
package tasks.webapplication.services;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import tasks.webapplication.JwtTokenProvider;
import tasks.webapplication.repositories.PlaceRepository;

import java.util.List;
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

    public ResponseEntity<Place> createPlace(PlaceCreateRequest placeCreateRequest, String token) {
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

            return ResponseEntity.ok(createdPlace);
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
}
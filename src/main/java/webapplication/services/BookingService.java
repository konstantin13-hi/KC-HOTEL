package webapplication.services;

import entities.Booking;
import dto.BookingRequest;
import entities.Place;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.Utils.JwtTokenUtils;
import webapplication.repositories.BookingRepository;
import webapplication.repositories.UserRepository;

import java.util.List;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final JwtTokenUtils jwtTokenUtils;
    @Autowired
    public BookingService(UserRepository userRepository, BookingRepository bookingRepository, JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.jwtTokenUtils = jwtTokenUtils;
    }


    public Booking createBooking(BookingRequest bookingRequest, String token) {
//        try {
        System.out.println("ssssssßdfwqąąć"+token);
            String subToken = token.substring(7);
            String email = jwtTokenUtils.getEmail(subToken);
            Long userId =  userRepository.findByEmail(email).get().getId();

            Booking booking = new Booking();
            booking.setCheckIn(bookingRequest.getCheckIn());
            booking.setCheckOut(bookingRequest.getCheckOut());
            booking.setNumberOfGuests(bookingRequest.getNumberOfGuests());
            booking.setName(bookingRequest.getName());
            booking.setPhone(bookingRequest.getPhone());
            booking.setPrice(bookingRequest.getPrice());

            User user = new User();
            user.setId(userId);
            booking.setUser(user);
            Place place = new Place();
            place.setId(bookingRequest.getPlaceId());
            booking.setPlace(place);

            return bookingRepository.save(booking);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error creating booking", e);
//        }
    }


    public List<Booking> getUserBookings(String token) {
        String subToken = token.substring(7);
        String email = jwtTokenUtils.getEmail(subToken);
        Long userId =  userRepository.findByEmail(email).get().getId();
        return bookingRepository.findByUserId(userId);
    }
}

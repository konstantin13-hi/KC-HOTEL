package webapplication.services;

import entities.Booking;
import webapplication.dto.BookingRequest;
import entities.Place;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.JwtTokenProvider;
import webapplication.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final JwtTokenProvider jwtService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, JwtTokenProvider jwtService) {
        this.bookingRepository = bookingRepository;
        this.jwtService = jwtService;
    }

    public Booking createBooking(BookingRequest bookingRequest, String token) {
        try {
            Long userId = Long.parseLong(jwtService.extractIdFromToken(token));

            Booking booking = new Booking();
            booking.setCheckIn(bookingRequest.getCheckIn());
            booking.setCheckOut(bookingRequest.getCheckOut());
            booking.setNumber_of_guests(bookingRequest.getNumberOfGuests());
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating booking", e);
        }
    }


    public List<Booking> getUserBookings(String token) {
        Long userId = Long.parseLong(jwtService.extractIdFromToken(token));
        return bookingRepository.findByUserId(userId);
    }
}

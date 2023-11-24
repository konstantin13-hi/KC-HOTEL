package webapplication.controllers;

import entities.Booking;
import dto.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapplication.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest, @CookieValue(name = "token") String token) {
        try {
            System.out.println(bookingRequest.toString());
            return ResponseEntity.ok(bookingService.createBooking(bookingRequest, token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getUserBookings(@CookieValue(name = "token") String token) {
        List<Booking> userBookings = bookingService.getUserBookings(token);
        return ResponseEntity.ok(userBookings);
    }


}

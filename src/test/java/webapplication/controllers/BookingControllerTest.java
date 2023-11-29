package webapplication.controllers;

import entities.Booking;
import entities.Place;
import entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webapplication.dto.BookingRequest;
import webapplication.services.BookingService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;


@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class BookingControllerTest {


    @MockBean
    private BookingService bookingService;

//    @Test
//    public void testCreateBooking() {
//
//        BookingRequest bookingRequest = new BookingRequest();
//        LocalDate localCheckIn = LocalDate.now();
//        LocalDate localCheckOut = LocalDate.now().plusDays(3);
//        Date checkInDate = Date.from(localCheckIn.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date checkOutDate = Date.from(localCheckOut.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        bookingRequest.setPlaceId(1L);
//        bookingRequest.setCheckIn(checkInDate);
//        bookingRequest.setCheckOut(checkOutDate);
//        bookingRequest.setNumberOfGuests(2);
//        bookingRequest.setName("John Doe");
//        bookingRequest.setPhone("+1234567890");
//        bookingRequest.setPrice(100.0);
//        String token = "testToken";
//        Booking mockBooking = new Booking();
//        mockBooking.setId(1L);
//        mockBooking.setPlace(new Place(1L,"A","B"));
//        mockBooking.setUser(new User(1L,"A","B","C"));
//        mockBooking.setCheckIn(checkInDate);
//        mockBooking.setCheckOut(checkOutDate);
//        mockBooking.setName("John Doe");
//        mockBooking.setPhone("123-456-7890");
//        mockBooking.setPrice(100.0);
//        mockBooking.setNumber_of_guests(2);
//
//        when(bookingService.createBooking(eq(bookingRequest), eq(token))).thenReturn(mockBooking);
//
//        ResponseEntity<Booking> responseEntity = bookingController.createBooking(bookingRequest, token);
//
//        verify(bookingService, times(1)).createBooking(eq(bookingRequest), eq(token));
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockBooking, responseEntity.getBody());
//    }

//    @Test
//    public void testCreateBookingException() {
//        // Создаем тестовые данные
//        BookingRequest bookingRequest = new BookingRequest(/* заполните данными для теста */);
//        String token = "testToken";
//
//        // Настроим поведение mock-объекта bookingService для выброса исключения
//        when(bookingService.createBooking(eq(bookingRequest), eq(token))).thenThrow(new RuntimeException("Test Exception"));
//
//        // Вызываем метод контроллера
//        ResponseEntity<Booking> responseEntity = bookingController.createBooking(bookingRequest, token);
//
//        // Проверяем, что метод контроллера взаимодействовал с сервисом и вернул ожидаемый результат при исключении
//        verify(bookingService, times(1)).createBooking(eq(bookingRequest), eq(token));
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }

//    @Test
//    public void testGetUserBookings() {
//        // Создаем тестовые данные
//        String token = "testToken";
//        List<Booking> mockUserBookings = Arrays.asList(new Booking(/* заполните данными для теста */));
//
//        // Настроим поведение mock-объекта bookingService
//        when(bookingService.getUserBookings(eq(token))).thenReturn(mockUserBookings);
//
//        // Вызываем метод контроллера
//
//        // Проверяем, что метод контроллера взаимодействовал с сервисом и вернул ожидаемый результат
//        verify(bookingService, times(1)).getUserBookings(eq(token));
////        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
////        assertEquals(mockUserBookings, responseEntity.getBody());
//    }

//    @Test
//    public void testGetUserBookingsException() {
//        // Создаем тестовые данные
//        String token = "testToken";
//
//        // Настроим поведение mock-объекта bookingService для выброса исключения
//        when(bookingService.getUserBookings(eq(token))).thenThrow(new RuntimeException("Test Exception"));
//
//        // Вызываем метод контроллера
//        ResponseEntity<List<Booking>> responseEntity = bookingController.getUserBookings(token);
//
//        // Проверяем, что метод контроллера взаимодействовал с сервисом и вернул ожидаемый результат при исключении
//        verify(bookingService, times(1)).getUserBookings(eq(token));
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//    }
}

package webapplication.dto;

import java.util.Date;

public class BookingRequest {

    private Long placeId;
    private Date checkIn;
    private Date checkOut;
    private int numberOfGuests;
    private String name;
    private String phone;
    private double price;

    public Long getPlaceId() {
        return placeId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "placeId=" + placeId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", numberOfGuests=" + numberOfGuests +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", price=" + price +
                '}';
    }
}

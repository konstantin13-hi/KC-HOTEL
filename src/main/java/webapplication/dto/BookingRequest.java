package webapplication.dto;

import java.util.Date;
import java.util.Objects;

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

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookingRequest() {
    }

    public BookingRequest(Long placeId, Date checkIn, Date checkOut, int numberOfGuests, String name, String phone, double price) {
        this.placeId = placeId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfGuests = numberOfGuests;
        this.name = name;
        this.phone = phone;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequest that = (BookingRequest) o;
        return numberOfGuests == that.numberOfGuests && Double.compare(price, that.price) == 0 && Objects.equals(placeId, that.placeId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, checkIn, checkOut, numberOfGuests, name, phone, price);
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

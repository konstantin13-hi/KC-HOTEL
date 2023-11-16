package entities;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Booking {
    private int id;
    private int placeId;
    private int userId;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private String name;
    private String phone;
    private BigDecimal price;


    public Booking(int id, int placeId, int userId, Timestamp checkIn, Timestamp checkOut, String name, String phone, BigDecimal price) {
        this.id = id;
        this.placeId = placeId;
        this.userId = userId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.name = name;
        this.phone = phone;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && placeId == booking.placeId && userId == booking.userId && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && Objects.equals(name, booking.name) && Objects.equals(phone, booking.phone) && Objects.equals(price, booking.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeId, userId, checkIn, checkOut, name, phone, price);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", placeId=" + placeId +
                ", userId=" + userId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", price=" + price +
                '}';
    }
}

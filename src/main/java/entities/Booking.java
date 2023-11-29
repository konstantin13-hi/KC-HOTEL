package entities;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "check_in", nullable = false)
    private Date checkIn;

    @Column(name = "check_out", nullable = false)
    private Date checkOut;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private Double price;

    @Column(nullable = false)
    private Integer number_of_guests;





    public Booking() {

    }

    public Booking(Long id, Place place, User user, Date checkIn, Date checkOut, String name, String phone, Double price, Integer number_of_guests) {
        this.id = id;
        this.place = place;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.number_of_guests = number_of_guests;
    }

    public Integer getNumber_of_guests() {
        return number_of_guests;
    }

    public void setNumber_of_guests(Integer number_of_guests) {
        this.number_of_guests = number_of_guests;
    }

    public Long getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public User getUser() {
        return user;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Double getPrice() {
        return price;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(place, booking.place) && Objects.equals(user, booking.user) && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && Objects.equals(name, booking.name) && Objects.equals(phone, booking.phone) && Objects.equals(price, booking.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, user, checkIn, checkOut, name, phone, price);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", place=" + place +
                ", user=" + user +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", price=" + price +
                '}';
    }
}

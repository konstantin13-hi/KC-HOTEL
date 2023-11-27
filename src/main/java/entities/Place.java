package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User ownerId;

    private String title;
    private String address;

    @ElementCollection
    private List<String> photos;

    private String description;

    @ElementCollection
    private List<String> perks;

    @Column(name = "extra_info")
    private String extraInfo;

    @Column(name = "check_in")
    private Integer checkIn;

    @Column(name = "check_out")
    private Integer checkOut;

    @Column(name = "max_guests")
    private Integer maxGuests;

    private Double price;

    public Place(Long id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPerks() {
        return perks;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public Integer getCheckIn() {
        return checkIn;
    }

    public Integer getCheckOut() {
        return checkOut;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setCheckIn(Integer checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Integer checkOut) {
        this.checkOut = checkOut;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public void copyPerks(List<String> source) {
        perks = new ArrayList<>(source);
    }

    public void copyPhotos(List<String> source) {
        photos = new ArrayList<>(source);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", photos=" + photos +
                ", description='" + description + '\'' +
                ", perks=" + perks +
                ", extraInfo='" + extraInfo + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", maxGuests=" + maxGuests +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id && ownerId == place.ownerId && checkIn == place.checkIn && checkOut == place.checkOut && maxGuests == place.maxGuests && Objects.equals(title, place.title) && Objects.equals(address, place.address) && Objects.equals(photos, place.photos) && Objects.equals(description, place.description) && Objects.equals(perks, place.perks) && Objects.equals(extraInfo, place.extraInfo) && Objects.equals(price, place.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, title, address, photos, description, perks, extraInfo, checkIn, checkOut, maxGuests, price);
    }

    public Place() {
    }
}


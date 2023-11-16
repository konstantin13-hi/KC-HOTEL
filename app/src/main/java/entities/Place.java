package entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Place {
    private int id;
    private int ownerId;
    private String title;
    private String address;
    private List<String> photos;
    private String description;
    private List<String> perks;
    private String extraInfo;
    private int checkIn;
    private int checkOut;
    private int maxGuests;
    private BigDecimal price;

    public Place(int id, int ownerId, String title, String address, List<String> photos,
                 String description, List<String> perks, String extraInfo,
                 int checkIn, int checkOut, int maxGuests, BigDecimal price) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.address = address;
        this.photos = photos;
        this.description = description;
        this.perks = perks;
        this.extraInfo = extraInfo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.maxGuests = maxGuests;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPerks() {
        return perks;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}


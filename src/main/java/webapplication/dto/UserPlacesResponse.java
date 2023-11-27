package webapplication.dto;

import java.util.List;
import java.util.Objects;

public class UserPlacesResponse {
    private Long id;
    private String title;
    private String address;
    private List<String> photos;
    private String description;
    private List<String> perks;
    private String extraInfo;
    private Integer checkIn;
    private Integer checkOut;
    private Integer maxGuests;
    private Double price;


    public UserPlacesResponse(Long id, String title, String address, List<String> photos, String description, List<String> perks, String extraInfo, Integer checkIn, Integer checkOut, Integer maxGuests, Double price) {
        this.id = id;
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

    public UserPlacesResponse() {

    }

    public UserPlacesResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlacesResponse that = (UserPlacesResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(address, that.address) && Objects.equals(photos, that.photos) && Objects.equals(description, that.description) && Objects.equals(perks, that.perks) && Objects.equals(extraInfo, that.extraInfo) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(maxGuests, that.maxGuests) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, address, photos, description, perks, extraInfo, checkIn, checkOut, maxGuests, price);
    }

    @Override
    public String toString() {
        return "UserPlacesResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", photos=" + String.join(", ", photos) +
                ", description='" + description + '\'' +
                ", perks=" + String.join(", ", perks) +
                ", extraInfo='" + extraInfo + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", maxGuests=" + maxGuests +
                ", price=" + price +
                '}';
    }
}

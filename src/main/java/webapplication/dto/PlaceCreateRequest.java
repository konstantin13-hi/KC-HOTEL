package webapplication.dto;

import java.util.List;
import java.util.Objects;

public class PlaceCreateRequest {
    private String title;
    private String address;
    private List<String> addedPhotos;
    private String description;
    private List<String> perks;
    private String extraInfo;
    private Integer checkIn;
    private Integer checkOut;
    private Integer maxGuests;
    private Double price;

    public PlaceCreateRequest() {
    }

    public PlaceCreateRequest(String title, String address, List<String> addedPhotos, String description, List<String> perks, String extraInfo, Integer checkIn, Integer checkOut, Integer maxGuests, Double price) {
        this.title = title;
        this.address = address;
        this.addedPhotos = addedPhotos;
        this.description = description;
        this.perks = perks;
        this.extraInfo = extraInfo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.maxGuests = maxGuests;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getAddedPhotos() {
        return addedPhotos;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddedPhotos(List<String> addedPhotos) {
        this.addedPhotos = addedPhotos;
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
        PlaceCreateRequest that = (PlaceCreateRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(address, that.address) && Objects.equals(addedPhotos, that.addedPhotos) && Objects.equals(description, that.description) && Objects.equals(perks, that.perks) && Objects.equals(extraInfo, that.extraInfo) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(maxGuests, that.maxGuests) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, address, addedPhotos, description, perks, extraInfo, checkIn, checkOut, maxGuests, price);
    }

    @Override
    public String toString() {
        return "PlaceCreateRequest{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", addedPhotos=" + String.join(", ", addedPhotos) +
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

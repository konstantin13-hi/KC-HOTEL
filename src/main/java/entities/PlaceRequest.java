package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaceRequest {
    private String title;
    private String address;
    @JsonProperty("addedPhotos")
    private List<String> addedPhotos;
    private String description;
    private List<String> perks;
    private String extraInfo;
    private Integer checkIn;
    private Integer checkOut;
    private Integer maxGuests;
    private Double price;

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getPhotos() {
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


    @Override
    public String toString() {
        return "PlaceRequest{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", addedPhotos=" + addedPhotos +
                ", description='" + description + '\'' +
                ", perks=" + perks +
                ", extraInfo='" + extraInfo + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", maxGuests=" + maxGuests +
                ", price=" + price +
                '}';
    }
}

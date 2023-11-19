package entities;

import java.util.List;

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
}

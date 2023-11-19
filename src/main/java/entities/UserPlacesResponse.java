package entities;

import java.util.List;

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

}

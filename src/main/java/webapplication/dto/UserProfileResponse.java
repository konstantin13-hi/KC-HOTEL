package webapplication.dto;

public class UserProfileResponse {

    private  String name;
    private  String email;


    public UserProfileResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserProfileResponse() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}

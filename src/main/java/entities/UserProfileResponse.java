package entities;

public class UserProfileResponse {

    private  String name;
    private  String email;
    private  Long id;

    public UserProfileResponse(String name, String email, Long id) {
        this.id=id;
        this.email=email;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}

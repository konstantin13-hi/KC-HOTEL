package webapplication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserLoginRequest {
    @NotBlank(message = "password is required")
    String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;

    public UserLoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public UserLoginRequest() {
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginRequest that = (UserLoginRequest) o;
        return Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, email);
    }

}

package dto;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String name;
    private String password;
//    private String confirmPassword;
    private String email;
}

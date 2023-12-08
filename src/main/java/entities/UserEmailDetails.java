package entities;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserEmailDetails extends org.springframework.security.core.userdetails.User {

    private String email;

    public UserEmailDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
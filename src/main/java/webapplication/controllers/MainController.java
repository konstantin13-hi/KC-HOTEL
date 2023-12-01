package webapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/unsecured")
    public String unsecured(){
        return "Unsecured data";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "Secured data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }

    @GetMapping("/admin")
    public String userDataAdmin(Principal principal){

        return principal.getName();
    }


}

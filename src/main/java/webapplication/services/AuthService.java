package webapplication.services;

import dto.*;
import entities.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webapplication.Utils.JwtTokenUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private  final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

        }
        catch (Exception exception){
            ResponseEntity.badRequest();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }

    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(),userLoginRequest.getPassword()));

        }
        catch (Exception exception){
            ResponseEntity.badRequest();
        }
        UserDetails userDetails = userService.loadUserByEmail(userLoginRequest.getEmail());
        String token = jwtTokenUtils.generateTokenWithEmail(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
//        return ResponseEntity.ok(new UserResponse(userDetails.getUsername()));

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", token);
        responseBody.put("name", userDetails.getUsername());
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(new UserResponse(userDetails.getUsername()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);


    }




    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
//        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
//            return ResponseEntity.badRequest().body("BAD ");
//        }
        if(userService.findByName(registrationUserDto.getName()).isPresent()){
            return ResponseEntity.badRequest().body("Exists ");
        }
        userService.createNewUser(registrationUserDto);

        return ResponseEntity.ok("GOOD");
    }



}

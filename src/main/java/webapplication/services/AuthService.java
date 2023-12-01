package webapplication.services;

import dto.JwtRequest;
import dto.JwtResponse;
import dto.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webapplication.Utils.JwtTokenUtils;

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

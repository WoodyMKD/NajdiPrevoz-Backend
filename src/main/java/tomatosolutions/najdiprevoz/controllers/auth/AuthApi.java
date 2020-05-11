package tomatosolutions.najdiprevoz.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tomatosolutions.najdiprevoz.models.auth.User;
import tomatosolutions.najdiprevoz.models.exceptions.security.EmailAlreadyExistsException;
import tomatosolutions.najdiprevoz.models.exceptions.security.UsernameAlreadyExistsException;
import tomatosolutions.najdiprevoz.payloads.security.LoginRequestDTO;
import tomatosolutions.najdiprevoz.payloads.security.SignUpRequestDTO;
import tomatosolutions.najdiprevoz.payloads.API.APIResponse;
import tomatosolutions.najdiprevoz.payloads.security.JwtAuthenticationResponse;
import tomatosolutions.najdiprevoz.services.AuthService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        String jwtToken = authService.authenticateUser(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequest) {
        User newUser = authService.registerUser(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getName()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(newUser.getUsername()).toUri();

        return ResponseEntity.created(location).body(new APIResponse("Корисникот е успешно регистриран.", HttpStatus.OK));
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<APIResponse> checkUsernameAvailability(@RequestParam(value="username") String username){
        boolean isAvailable = authService.isUsernameAvailable(username);
        if (!isAvailable) throw new UsernameAlreadyExistsException(username);
        return ResponseEntity.ok(new APIResponse(isAvailable, HttpStatus.OK));
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<APIResponse> checkEmailAvailability(@RequestParam(value = "email") String email) {
        boolean isAvailable = authService.isEmailAvailable(email);
        if (!isAvailable) throw new EmailAlreadyExistsException(email);
        return ResponseEntity.ok(new APIResponse(isAvailable, HttpStatus.OK));
    }
}

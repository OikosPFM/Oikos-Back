package com.pfm.oikos.jwtpack;

import com.pfm.oikos.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Usuario request
            ) {   try {
                return ResponseEntity.ok(authService.register(request));
            } catch (IllegalArgumentException e) {
                // Devolver 409 Conflict si el email ya existe
                String errorMessage = "El correo electrónico ya está registrado.";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse(null, errorMessage));
            }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Usuario request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

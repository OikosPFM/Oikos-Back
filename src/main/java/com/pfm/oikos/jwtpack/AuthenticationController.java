package com.pfm.oikos.jwtpack;

import com.pfm.oikos.entity.Usuario;
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
            @RequestBody Usuario request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Usuario request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    /*
     * @SuppressWarnings("rawtypes")
     * 
     * @PostMapping("/refresh_token")
     * public ResponseEntity refreshToken(
     * HttpServletRequest request,
     * HttpServletResponse response
     * ) {
     * return authService.refreshToken(request, response);
     * }
     */
}

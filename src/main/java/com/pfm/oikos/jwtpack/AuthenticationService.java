package com.pfm.oikos.jwtpack;

import com.pfm.oikos.entity.Propiedad;
import com.pfm.oikos.repository.PropiedadRepository;

import com.pfm.oikos.entity.Usuario;
import com.pfm.oikos.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PropiedadRepository propiedadRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            PropiedadRepository propiedadRepository,
            AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.propiedadRepository = propiedadRepository;
        // this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Usuario request) {
        // check if user already exist. if exist then authenticate the user
        /*
         * if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
         * return new AuthenticationResponse(null, "User already exists");
         * }
         */
        // Crear un nuevo usuario basado en la entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setPrimerApellido(request.getPrimerApellido());
        usuario.setSegundoApellido(request.getSegundoApellido());
        usuario.setEmail(request.getEmail());
        usuario.setDni(request.getDni());
        usuario.setTelefono(request.getTelefono());
        usuario.setContraseña(passwordEncoder.encode(request.getContraseña()));

        // Asignar roles al usuario
        usuario.setRol(request.getRol());
        System.out.println("Rol del usuario: " + usuario.getRol());

        if (request.getPropiedad() != null && request.getPropiedad().getIdPropiedad() != null) {
            // Si se proporciona un idPropiedad válido, asignarlo al usuario
            Propiedad propiedad = propiedadRepository.findById(request.getPropiedad().getIdPropiedad())
                    .orElseThrow(() -> new RuntimeException(
                            "Propiedad no encontrada con id: " + request.getPropiedad().getIdPropiedad()));
            usuario.setPropiedad(propiedad);
        }

        // Guardar el usuario en la base de datos
        usuario = usuarioRepository.save(usuario);

        // Generar el token
        String token = jwtService.generateToken(usuario);

        return new AuthenticationResponse(token);
    }

    /*
     * private void revokeAllTokenByUser(Usuario usuario) {
     * List<Token> validTokens =
     * tokenRepository.findAllAccessTokensByUser(usuario.getIdUsuario());
     * if(validTokens.isEmpty()) {
     * return;
     * }
     * 
     * validTokens.forEach(t-> {
     * t.setLoggedOut(true);
     * });
     * 
     * tokenRepository.saveAll(validTokens);
     * }
     * private void saveUserToken(String accessToken, String refreshToken, Usuario
     * usuario) {
     * Token token = new Token();
     * token.setAccessToken(accessToken);
     * token.setRefreshToken(refreshToken);
     * token.setLoggedOut(false);
     * token.setUsuario(usuario);
     * tokenRepository.save(token);
     * }
     * 
     * public ResponseEntity refreshToken(
     * HttpServletRequest request,
     * HttpServletResponse response) {
     * // extract the token from authorization header
     * String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
     * 
     * if(authHeader == null || !authHeader.startsWith("Bearer ")) {
     * return new ResponseEntity(HttpStatus.UNAUTHORIZED);
     * }
     * 
     * String token = authHeader.substring(7);
     * 
     * // extract username from token
     * String email = jwtService.extractUsername(token);
     * 
     * // check if the user exist in database
     * Usuario usuario = usuarioRepository.findByEmail(email)
     * .orElseThrow(()->new RuntimeException("No user found"));
     * 
     * // check if the token is valid
     * if(jwtService.isValidRefreshToken(token, usuario)) {
     * // generate access token
     * String accessToken = jwtService.generateAccessToken(usuario);
     * String refreshToken = jwtService.generateRefreshToken(usuario);
     * 
     * revokeAllTokenByUser(usuario);
     * saveUserToken(accessToken, refreshToken, usuario);
     * 
     * return new ResponseEntity(new AuthenticationResponse(accessToken,
     * refreshToken, "New token generated"), HttpStatus.OK);
     * }
     * 
     * return new ResponseEntity(HttpStatus.UNAUTHORIZED);
     * 
     * }
     */

    public AuthenticationResponse authenticate(Usuario request) {
        try {
            // Autenticación de credenciales
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getContraseña()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }

        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verificar el estado del usuario
        if (!user.getEstado()) {
            throw new RuntimeException("User is not active");
        }

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}

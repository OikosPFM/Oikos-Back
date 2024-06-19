package com.pfm.oikos.jwtpack;

import com.pfm.oikos.entity.Usuario;
//import com.pfm.oikos.jwtpack.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
	
	private final String SECRET_KEY = "c98da01a28c80d888b97e1e89bacada39cc558b4d65eeb77363fee4b411e98b4";

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        /*boolean validToken = tokenRepository
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);*/

        return (username.equals(user.getUsername())) && !isTokenExpired(token) ;
    }

    public boolean isValidRefreshToken(String token, Usuario usuario) {
        String username = extractUsername(token);

        /*boolean validRefreshToken = tokenRepository
                .findByRefreshToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);*/

        return (username.equals(usuario.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    /*public String generateAccessToken(Usuario usuario) {
        return generateToken(usuario, accessTokenExpire);
    }

    public String generateRefreshToken(Usuario usuario) {
        return generateToken(usuario, refreshTokenExpire );
    }*/

    public String generateToken(Usuario usuario) {
        String token = Jwts
                .builder()
                .subject(usuario.getUsername())
                .claim("idFinca", usuario.getFincaId())  // Add finca ID as a claim
                .claim("idUsuario", usuario.getIdUsuario())  // Add finca ID as a claim
                .claim("rol", usuario.getRol())  // Add finca ID as a claim
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSigninKey()) // Usa un método para obtener la clave de firma segura
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

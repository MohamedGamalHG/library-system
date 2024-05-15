package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.domains.entities.JpaUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "MohamedGamalHamza12654789fasdfasdfadfadfafdadfasdfafdsfadfateefdfasdfafafaffffasfa";

    public boolean isValid(String token, UserDetails userDetails)
    {
        String user = extractUserName(token);
        return user.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token)
    {
        return extractClaim(token,Claims::getExpiration);
    }
    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }
    private  <T> T extractClaim(String token , Function<Claims,T> resolver)
    {
        Claims c = extractAllClaims(token);
        return  resolver.apply(c);
    }
    private Claims extractAllClaims(String token)
    {
        return Jwts.parser().verifyWith(signKey()).build().parseSignedClaims(token).getPayload();
    }
    public String generateKey(JpaUser user)
    {
        String token = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis() * 60*40 ))
                .expiration(new Date(System.currentTimeMillis() * 60 * 60 ))
                .signWith(signKey())
                .compact();
        return  token;
    }

    private SecretKey signKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

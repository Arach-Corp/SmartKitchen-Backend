package com.arachcorp.smartkitchen.security.jwt;

import com.arachcorp.smartkitchen.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class JwtService {


    @Value("${security.jwt.expire-in}")
    private String expires_in;

    @Value("${security.jwt.secret-key}")
    private String secretKey;


    public String createToken(User user) {
        final long expiresIn = Long.parseLong(expires_in);
        LocalDateTime expiresTime = LocalDateTime.now().plusMinutes(expiresIn);
        Date data = Date.from(expiresTime.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isValidToken(String token) throws ExpiredJwtException {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime dtt = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isBefore(dtt);
        } catch (ExpiredJwtException e) {
            log.info("Expired token: " + token);
            return false;
        } catch (Exception e){
            return false;
        }
    }


    public String getUsername(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }

    public Long getExpiresIn(String token) throws ExpiredJwtException {
        final Date expireDate = getClaims(token).getExpiration();
        final Date now = new Date();
        return expireDate.getTime() - now.getTime();
    }


    public boolean isValidBearerToken(String bearerToken){
        return bearerToken != null && bearerToken.startsWith("Bearer") && bearerToken.split(" ").length == 2;
    }
}
package com.otdot.hgm.utils;

import com.otdot.hgm.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    private final String jwtSecret = "TivoliRadioMustBeFancyRadioAndKeepInMindToUseStrongKeys987654321";
    private static final long ONE_DAY = Duration.of(1, TimeUnit.DAYS.toChronoUnit()).toMillis();

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes); // Luo avain omasta tekstistä
//          return Jwts.SIG.HS256.key().build(); // Luo vahva avain automaattisesti tokenin luomisessa voi käyttää SecretKey tai Key classeja
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + ONE_DAY))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {} " +  e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {} " +  e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {} " +  e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {} " +  e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {} " +  e.getMessage());
        }

        return false;
    }

}

package com.otdot.hgm.utils;

import com.otdot.hgm.security.UserDetailsImpl;
import com.otdot.hgm.services.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    private final String jwtSecret = "TivoliRadio";
    private final long jwtExpirationMs = Duration.of(1, TimeUnit.DAYS.toChronoUnit()).toMillis();

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes); // Luo avain omasta tekstistä
//          return Jwts.SIG.HS256.key().build(); // Luo vahva avain automaattisesti tokenin luomisessa voi käyttää SecretKey tai Key classeja
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(now)
                .setExpiration(new Date(now.getTime() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
    }

}

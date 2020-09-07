package com.example.demo.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class JwtToken {

    public String createClientToken() {

        final List<String> roles = Arrays.asList("role1","role2");
        final List<String> privileges = Arrays.asList("prev1","prev2");

        Map<String, Object> claims = new HashMap<>();
        claims.put(Tokens.CLAIM_ROLES, String.join(",", roles));
        claims.put(Tokens.CLAIM_PRIVILEGES, privileges);
        claims.put(Tokens.CLAIM_PASSWORD_RESET, "required");
        claims.put(Tokens.CLAIM_ORG_ID,"org_id");
        Calendar calendarExpirationTime = new GregorianCalendar();
        calendarExpirationTime.add(Calendar.DATE, 30);

        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withHeader(claims)
                .withIssuer("DemoApp")
                .withIssuedAt(new Date())
                .withExpiresAt(calendarExpirationTime.getTime())
                .withSubject("Subject is Hello")
                .sign(algorithm);

    }

    public Jws<Claims> decodeToken(String token) throws UnsupportedEncodingException {
        DecodedJWT decodedJWT = JWT.decode(token);
        String header = decodedJWT.getHeader();
        JwtTokenResponse build = JwtTokenResponse.builder()
                .algorithm(decodedJWT.getAlgorithm())
                .audience(decodedJWT.getAudience())
                .claims(decodedJWT.getClaims())
                .issuer(decodedJWT.getIssuer())
                .issuedAt(decodedJWT.getIssuedAt())
                .expiresAt(decodedJWT.getExpiresAt())
                .subject(decodedJWT.getSubject())
                .header(decodedJWT.getHeader())
                .build();
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey("secret".getBytes("UTF-8"))
                .parseClaimsJws(token);
        return claimsJws;
    }
}

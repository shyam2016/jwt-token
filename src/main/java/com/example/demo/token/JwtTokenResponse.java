package com.example.demo.token;

import com.auth0.jwt.interfaces.Claim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenResponse {
    private String algorithm;
    private List<String> audience;
    private Map<String, Claim> claims;
    private String issuer;
    private Date issuedAt;
    private Date expiresAt;
    private String subject;
    private String header;
}

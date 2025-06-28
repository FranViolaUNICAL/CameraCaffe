package com.example.cameracaffe.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "L8mAvS9dR1eYtS5wQjKlVxZcNsDuFgHe";
    private final long expirationTime = 86400000L;

    public String generateToken(String username) throws JOSEException {
        JWSSigner signer = new MACSigner(secret);
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }


    public boolean validateToken(String token, UserDetails userDetails) throws ParseException, JOSEException {
        SignedJWT jwt = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secret);

        String usernameFromToken = jwt.getJWTClaimsSet().getSubject();

        return jwt.verify(verifier) &&
                !isExpired(jwt) &&
                usernameFromToken.equals(userDetails.getUsername());
    }

    public String extractUsername(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet().getSubject();
    }

    private boolean isExpired(SignedJWT jwt) throws ParseException {
        return jwt.getJWTClaimsSet().getExpirationTime().before(new Date());
    }
}

package com.undersnowmans.forum.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.undersnowmans.forum.jwt.Token;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.util.Date;
import java.util.function.Function;

@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
@Setter
public class AccessTokenJwsStringSerializer implements Function<Token, String> {

    private final JWSSigner jwsSigner;

    private JWSAlgorithm jwsAlgorithm = JWSAlgorithm.ES256;

    @Override
    public String apply(Token token) {
        var jwsHeader = new JWSHeader.Builder(this.jwsAlgorithm)
                                                .keyID(token.id().toString())
                                                .build();

        var claimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id().toString())
                .subject(token.username())
                .issueTime(Date.from(token.createdAt()))
                .expirationTime(Date.from(token.expiresAt()))
                .claim("authorities", token.authorities())
                .build();

        var signedJwt = new SignedJWT(jwsHeader, claimsSet);
        try {
            signedJwt.sign(this.jwsSigner);
            return signedJwt.serialize();
        } catch (JOSEException e) {
           log.error(e.getMessage());
        }

        return null;
    }
}
